package com.github.gaskapiotr.stockmarketsim.wallet;

public interface WalletInternalAPI {
    void addWalletIfDoesNotExist(String wallet_id);
    void sellStock(String wallet_id, String stock_name);
    void increaseStock(String wallet_id, String stock_name);
}
