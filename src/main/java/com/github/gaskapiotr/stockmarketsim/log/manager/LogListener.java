package com.github.gaskapiotr.stockmarketsim.log.manager;

import com.github.gaskapiotr.stockmarketsim.transaction.BuyStockEvent;
import com.github.gaskapiotr.stockmarketsim.transaction.SellStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogListener {
    private final LogManager logManager;

    @ApplicationModuleListener
    void on(BuyStockEvent event) {
        logManager.addBuyLog(
                event.wallet_id(),
                event.stock_name()
        );
    }

    @ApplicationModuleListener
    void on(SellStockEvent event) {
        logManager.addSellLog(
                event.wallet_id(),
                event.stock_name()
        );
    }
}
