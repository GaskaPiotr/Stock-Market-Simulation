package com.github.gaskapiotr.stockmarketsim.bank;

public class BankStockInvalidQuantityException extends RuntimeException {
    public BankStockInvalidQuantityException(String stock_name, int quantity) {
        super("Cannot add \"" + stock_name + "\" with quantity \"" + quantity + "\" below one");
    }
}
