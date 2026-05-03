package com.github.gaskapiotr.stockmarketsim.bank;

public interface BankExternalAPI {
     StocksDTO getAllStocks();
     void setStocks(StocksDTO stocks);
}
