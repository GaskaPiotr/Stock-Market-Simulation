package com.github.gaskapiotr.stockmarketsim.transaction.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.transaction.BuyStockEvent;
import com.github.gaskapiotr.stockmarketsim.transaction.SellStockEvent;
import com.github.gaskapiotr.stockmarketsim.transaction.TransactionExternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletInternalAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockTransactionManager implements TransactionExternalAPI {
    private final BankInternalAPI bankInternalAPI;
    private final WalletInternalAPI walletInternalAPI;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void sellStock(String wallet_id, String stock_name) {
        prepareBeforeTransaction(wallet_id, stock_name);
        // TODO if no stock in the wallet fail with 400
        walletInternalAPI.sellStock(wallet_id, stock_name);
        bankInternalAPI.addStock(stock_name);
        publishSellStockEvent(wallet_id, stock_name);
    }

    private void prepareBeforeTransaction(String wallet_id, String stock_name) {
        // TODO if does not exist fail with 400
        if (!bankInternalAPI.doesStockExist(stock_name)) {
            // TODO throw exception `
        }
        walletInternalAPI.addWalletIfDoesNotExist(wallet_id);
    }

    private void publishSellStockEvent(String wallet_id, String stock_name) {
        eventPublisher.publishEvent(new SellStockEvent(wallet_id, stock_name));
    }

    @Override
    @Transactional
    public void buyStock(String wallet_id, String stock_name) {
        prepareBeforeTransaction(wallet_id, stock_name);
        bankInternalAPI.decreaseStock(stock_name);
        walletInternalAPI.increaseStock(wallet_id, stock_name);
        publishBuyStockEvent(wallet_id, stock_name);
    }

    private void publishBuyStockEvent(String wallet_id, String stock_name) {
        eventPublisher.publishEvent(new BuyStockEvent(wallet_id, stock_name));
    }
}
