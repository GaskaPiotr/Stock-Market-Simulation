package com.github.gaskapiotr.stockmarketsim.gateway.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TradeType {
    @JsonProperty("buy")
    BUY,
    @JsonProperty("sell")
    SELL
}
