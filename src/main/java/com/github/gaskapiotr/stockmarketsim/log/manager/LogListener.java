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
        // TODO handle buy event
    }

    @ApplicationModuleListener
    void on(SellStockEvent event) {
        // TODO handle sell event
    }
}
