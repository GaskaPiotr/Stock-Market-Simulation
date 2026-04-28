package com.github.gaskapiotr.stockmarketsim.log.manager;

import com.github.gaskapiotr.stockmarketsim.log.LogExternalAPI;
import org.springframework.stereotype.Service;

@Service
public class LogManager implements LogExternalAPI {

    private void addLog(String type, String wallet_id, String stock_name) {
        // TODO add log
    }

    void addBuyLog(String wallet_id, String stock_name) {
        addLog("buy", wallet_id, stock_name);
    }
    void addSellLog(String wallet_id, String stock_name) {
        addLog("sell", wallet_id, stock_name);
    }
}
