package com.github.gaskapiotr.stockmarketsim.bank;

public interface BankInternalAPI {
    boolean doesStockExist(String name);
    void addStock(String stock_name);
}
