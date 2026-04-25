package com.github.gaskapiotr.stockmarketsim.wallet;

public record SellStockEvent(
        String wallet_id,
        String stock_name
) {}