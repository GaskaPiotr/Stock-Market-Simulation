package com.github.gaskapiotr.stockmarketsim.transaction;

public record SellStockEvent(
        String wallet_id,
        String stock_name
) {}