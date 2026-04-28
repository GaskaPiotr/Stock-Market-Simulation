package com.github.gaskapiotr.stockmarketsim.log.manager;

import com.github.gaskapiotr.stockmarketsim.log.LogExternalAPI;
import com.github.gaskapiotr.stockmarketsim.log.entity.Log;
import com.github.gaskapiotr.stockmarketsim.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogManager implements LogExternalAPI {
    private final LogRepository logRepository;

    private void addLog(String type, String wallet_id, String stock_name) {
        Log log = createLog(type, wallet_id, stock_name);
        logRepository.save(log);
    }

    private Log createLog(String type, String wallet_id, String stock_name) {
        Log log = new Log();
        log.setType(type);
        log.setWallet_id(wallet_id);
        log.setStock_name(stock_name);
        return log;
    }

    void addBuyLog(String wallet_id, String stock_name) {
        addLog("buy", wallet_id, stock_name);
    }
    void addSellLog(String wallet_id, String stock_name) {
        addLog("sell", wallet_id, stock_name);
    }
}
