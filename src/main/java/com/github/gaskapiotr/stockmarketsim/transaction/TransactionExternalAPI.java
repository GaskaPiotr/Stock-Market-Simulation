package com.github.gaskapiotr.stockmarketsim.transaction;

public interface TransactionExternalAPI {
    void sellStock(String wallet_id, String stock_name);
}
