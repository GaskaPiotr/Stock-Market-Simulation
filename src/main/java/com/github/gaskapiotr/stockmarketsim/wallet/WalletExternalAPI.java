package com.github.gaskapiotr.stockmarketsim.wallet;

public interface WalletExternalAPI {
    WalletDTO getWallet(String wallet_id);
    int getWalletStockQuantity(String wallet_id, String stock_name);
}
