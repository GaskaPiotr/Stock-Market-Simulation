CREATE TABLE bank_stock (
    name VARCHAR(255) PRIMARY KEY,
    quantity INT NOT NULL
);

CREATE TABLE wallet (
    id VARCHAR(255) PRIMARY KEY
);

CREATE TABLE wallet_stock (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    wallet_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(id)
);

CREATE TABLE stock_log (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    wallet_id VARCHAR(255) NOT NULL,
    stock_name VARCHAR(255) NOT NULL
);