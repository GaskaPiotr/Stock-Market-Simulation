# StockMarketSim

A simplified, high-availability stock market exchange simulation. This API facilitates direct buy/sell operations between dynamic user wallets and a central Bank entity, which acts as the sole liquidity provider.

Built with a focus on robust architecture, this project features a **self-healing, load-balanced, multi-node backend** deployed via Docker Compose, built on top of a clean Spring Modulith foundation.

## Business Logic & Constraints
This is a simplified exchange without order books. The following domain rules apply:
* **Fixed Pricing:** All stocks have a fixed face value of `1` (no price fluctuation).
* **Stateless Wallets:** Wallet balances are not tracked. Wallets are created automatically upon their first transaction.
* **Single Transactions:** Buy/sell operations are executed immediately for exactly `1` stock at a time (no bulk operations).
* **Sole Liquidity:** The Bank is the only entity providing liquidity. If the Bank runs out of a stock, buys fail. If a wallet runs out of a stock, sells fail.
* **Clean Slate:** The application boots with an empty database (no wallets, empty bank account).

## Tech Stack
* **Language:** Java 25
* **Framework:** Spring Boot 4.0.5
* **Architecture:** Spring Modulith 2.0.5 (Domain-Driven Design)
* **Database:** PostgreSQL 15
* **Migrations:** Flyway (Distributed Lock Safe)
* **Mapping:** MapStruct & Lombok
* **DevOps:** Docker, Docker Compose, NGINX (Load Balancer)

## Prerequisites & How to Run

Thanks to a **multi-stage Docker build**, you do **not** need Java or Maven installed on your host machine to run or build this project. The container handles the entire build lifecycle internally.

**Only Docker Desktop is required.**

### 1. Download the Project
First, clone the repository and navigate into the project folder:
```bash
git clone https://github.com/GaskaPiotr/Stock-Market-Simulation
cd Stock-Market-Simulation
```

### 2. Start the Cluster
The application includes cross-platform startup scripts that dynamically bind the NGINX load balancer to a port of your choice.

**Windows:**
```cmd
.\start.bat 8080
```

**macOS / Linux:**
```bash
./start.sh 8080
```

*Note: The cluster utilizes strict health checks and sequential Flyway migrations. Please allow ~15-30 seconds for the database to provision and the Spring Boot nodes to become healthy before accepting traffic.*

### 3. Stop & Reset the Cluster
To safely tear down the cluster and **completely wipe the database** for a clean slate:

**Windows:**
```cmd
.\stop.bat
```

**macOS / Linux:**
```bash
./stop.sh
```

---

## High Availability Architecture

This application is deployed as a resilient cluster:
1. **NGINX Load Balancer:** Acts as the entry point, routing traffic in a Round-Robin fashion.
2. **API Nodes (`api-1`, `api-2`):** Two identical Spring Boot instances running concurrently.
3. **Database (`db`):** A persistent PostgreSQL instance.

**Highlight Feature: The Chaos Endpoint**
To prove the cluster's self-healing capabilities, you can trigger `POST /chaos`. This will intentionally kill the specific API instance handling the request. NGINX will immediately route subsequent requests to the surviving node, while Docker automatically spins up a replacement node in the background without dropping the database state.

---

## API Documentation

Base URL: `http://localhost:<PORT>`

### Bank Operations

**Set Bank State**
* `POST /stocks`
* Initializes or overwrites the Bank's inventory.
* **Body:**
  ```json
   {
    "stocks": [
      {
        "name": "stock1", 
        "quantity": 99
      },
      {
        "name": "stock2", 
        "quantity": 1
      }
    ]
  }
  ```
* **Response:** `200 OK`

**Get Bank State**
* `GET /stocks`
* **Response:**
  ```json
  {
    "stocks": [
      {
        "name": "stock1", 
        "quantity": 99
      },
      {
        "name": "stock2", 
        "quantity": 1
      }
    ]
  }
  ```

### Wallet Operations

**Trade Stock (Buy/Sell)**
* `POST /wallets/{wallet_id}/stocks/{stock_name}`
* Executes a single stock trade. Automatically creates the wallet if it does not exist.
* **Body:**
  ```json
  {
    "type": "buy" 
  }
  ```
  *(Accepts `"buy"` or `"sell"`)*
* **Responses:**
    * `200 OK`: Trade successful.
    * `400 Bad Request`: Bank has insufficient stock (for buy) or wallet has insufficient stock (for sell).
    * `404 Not Found`: Stock does not exist in the system.

**Get Wallet State**
* `GET /wallets/{wallet_id}`
* **Response:**
  ```json
  {
    "id": "12qdsdadsa",
    "stocks": [
      {
        "name": "stock1", 
        "quantity": 99
      }
    ]
  }
  ```

**Get Specific Wallet Stock**
* `GET /wallets/{wallet_id}/stocks/{stock_name}`
* **Response:** `99` (Raw integer representing quantity)

### System Operations

**Get Audit Log**
* `GET /log`
* Returns the historical log of all successful transactions in order of occurrence.
* **Response:**
  ```json
  {
    "log": [
      {
        "type": "buy", 
        "wallet_id": "23qdsadsa", 
        "stock_name": "stock1"
      },
      {
        "type": "sell", 
        "wallet_id": "12qdsdadsa", 
        "stock_name": "stock1"
      }
    ]
  }
  ```

**Chaos (Testing)**
* `POST /chaos`
* Kills the current running instance to test NGINX failover.

---

## Project Structure
The codebase utilizes Spring Modulith to enforce strict logical boundaries between domains:

```text
src/main/java/com/github/gaskapiotr/stockmarketsim/
├── bank/           Bank inventory management
├── gateway/        Central API controllers
├── log/            Audit logging system
├── transaction/    Trade execution and validation
└── wallet/         User wallet management
```
*Each domain is fully encapsulated with its own `entity`, `repository`, `manager`, and `mapper`.*