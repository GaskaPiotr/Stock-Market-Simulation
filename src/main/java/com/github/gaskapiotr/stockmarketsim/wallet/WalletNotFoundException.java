package com.github.gaskapiotr.stockmarketsim.wallet;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String wallet_id) {
        super("Wallet \"" + wallet_id + "\" does not exist");
    }
}
