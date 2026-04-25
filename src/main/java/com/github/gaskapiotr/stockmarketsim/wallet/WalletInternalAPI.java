package com.github.gaskapiotr.stockmarketsim.wallet;

public interface WalletInternalAPI {
    void createWalletIfDoesNotExist(String wallet_id);
    void sellStock(String wallet_id, String stock_name);
}
