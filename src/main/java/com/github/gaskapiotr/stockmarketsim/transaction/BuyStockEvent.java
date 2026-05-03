package com.github.gaskapiotr.stockmarketsim.transaction;

public record BuyStockEvent(
        String wallet_id,
        String stock_name
) {}
