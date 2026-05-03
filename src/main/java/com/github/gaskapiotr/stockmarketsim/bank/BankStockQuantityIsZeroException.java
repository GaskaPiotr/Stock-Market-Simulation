package com.github.gaskapiotr.stockmarketsim.bank;

public class BankStockQuantityIsZeroException extends RuntimeException {
    public BankStockQuantityIsZeroException(String name) {
        super("Stock \"" + name + "\" quantity is zero");
    }
}
