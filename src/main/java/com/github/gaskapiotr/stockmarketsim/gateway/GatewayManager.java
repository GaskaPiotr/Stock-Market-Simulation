package com.github.gaskapiotr.stockmarketsim.gateway;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.StocksDTO;
import com.github.gaskapiotr.stockmarketsim.gateway.request.TradeRequest;
import com.github.gaskapiotr.stockmarketsim.gateway.request.TradeType;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/wallets/{wallet_id}")
    public WalletDTO getWallet(@PathVariable String wallet_id) {
        // TODO return get wallet
    }

    @GetMapping("/stocks")
    public StocksDTO getAllStocks() {
        return bankExternalAPI.getAllStocks();
    }

    @PostMapping("/stocks")
    public void addStocks(@RequestBody StocksDTO stocks) {
        bankExternalAPI.addStocks(stocks);
    }
}
