package com.github.gaskapiotr.stockmarketsim.bank;

import java.util.List;

public interface BankExternalAPI {
     List<BankStockDTO> getAllStocks();
     void addStocks(List<BankStockDTO> stocks);
}
