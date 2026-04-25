package com.github.gaskapiotr.stockmarketsim.transaction.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.transaction.TransactionExternalAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionManager implements TransactionExternalAPI {
    private final BankInternalAPI bankInternalAPI;
    public void sellStock(String wallet_id, String stock_name) {

    }

    private void prepareBeforeTransaction(String wallet_id, String stock_name) {
        // TODO if does not exist fail with 400
        if (!bankInternalAPI.doesStockExist(stock_name)) {
            // TODO throw exception `
        }
    }
}
