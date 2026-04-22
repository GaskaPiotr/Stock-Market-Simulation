package com.github.gaskapiotr.stockmarketsim.bank;

import java.util.List;

public interface BankExternalAPI {
     void addStocks(List<BankStockDTO> stocks);
}
