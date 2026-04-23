package com.github.gaskapiotr.stockmarketsim.wallet.manager;

import com.github.gaskapiotr.stockmarketsim.wallet.WalletExternalAPI;
import org.springframework.stereotype.Service;

@Service
public class WalletManager implements WalletExternalAPI {
    public void sellStock(String wallet_id, String stock_name) {
        // TODO add logic
        // TODO check if stock exist in bank
        // TODO if no stock in the wallet fail with 400
        // TODO if wallet doesn't exist create it
        // TODO sell stock and add event
    }
}
