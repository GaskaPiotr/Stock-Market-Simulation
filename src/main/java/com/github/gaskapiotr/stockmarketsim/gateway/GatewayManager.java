package com.github.gaskapiotr.stockmarketsim.gateway;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankStockDTO;
import com.github.gaskapiotr.stockmarketsim.gateway.request.TradeRequest;
import com.github.gaskapiotr.stockmarketsim.gateway.request.TradeType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GatewayManager {
    private final BankExternalAPI bankExternalAPI;

    @PostMapping("/wallets/{wallet_id}/stocks/{stock_name}")
    public void tradeStock(
            @PathVariable String wallet_id,
            @PathVariable String stock_name,
            @RequestBody TradeRequest tradeRequest) {
        if (tradeRequest.type() == TradeType.BUY) {
            // TODO bank sells then user buys
        } else if (tradeRequest.type() == TradeType.SELL) {
            // TODO user sells then bank buys
        }
        // TODO return HTTP response
    }

    @PostMapping("/stocks")
    public void addStocks(@RequestBody List<BankStockDTO> stocks) {
        bankExternalAPI.addStocks(stocks);
    }
}
