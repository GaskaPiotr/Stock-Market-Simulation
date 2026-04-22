package com.github.gaskapiotr.stockmarketsim.wallet;

import java.util.List;

public record WalletDTO(
        int id,
        List<WalletStockDTO> stocks
) {}
