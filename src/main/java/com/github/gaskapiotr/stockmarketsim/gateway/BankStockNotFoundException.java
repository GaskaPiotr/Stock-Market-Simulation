package com.github.gaskapiotr.stockmarketsim.gateway;

public class BankStockNotFoundException extends RuntimeException {
    public BankStockNotFoundException(String name) {
        super("Stock \"" + name + "\" not found");
    }
}
