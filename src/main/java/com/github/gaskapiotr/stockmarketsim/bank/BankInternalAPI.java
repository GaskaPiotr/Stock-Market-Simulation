package com.github.gaskapiotr.stockmarketsim.bank;

public interface BankInternalAPI {
    boolean doesStockExist(String name);
    void increaseStock(String stock_name);
    void decreaseStock(String stock_name);
}
