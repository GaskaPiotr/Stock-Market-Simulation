package com.github.gaskapiotr.stockmarketsim.bank;

import java.util.List;

public record StocksDTO(
        List<BankStockDTO> stocks
) {}
