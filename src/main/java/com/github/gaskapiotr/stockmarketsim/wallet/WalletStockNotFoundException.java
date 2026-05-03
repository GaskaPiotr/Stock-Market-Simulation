package com.github.gaskapiotr.stockmarketsim.wallet;

public class WalletStockNotFoundException extends RuntimeException {
    public WalletStockNotFoundException(String wallet_id, String name) {
        super("Stock \"" + name + "\" not found in wallet \"" + wallet_id + "\"");
    }
}
