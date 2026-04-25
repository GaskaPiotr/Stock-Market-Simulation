package com.github.gaskapiotr.stockmarketsim.bank.manager;

import com.github.gaskapiotr.stockmarketsim.wallet.SellStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankListener {
    private final BankManager bankManager;

    @ApplicationModuleListener
    void on(SellStockEvent event) {
        bankManager.addStock(event.stock_name());
    }
}
