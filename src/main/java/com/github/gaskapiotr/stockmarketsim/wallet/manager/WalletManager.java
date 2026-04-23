package com.github.gaskapiotr.stockmarketsim.wallet.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletExternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import com.github.gaskapiotr.stockmarketsim.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WalletManager implements WalletExternalAPI {
    private final WalletRepository walletRepository;
    private final BankInternalAPI bankInternalAPI;

    @Override
    @Transactional
    public void sellStock(String wallet_id, String stock_name) {
        // TODO add logic
        // TODO check if stock exist in bank
        if (!bankInternalAPI.doesStockExist(stock_name)) {
            // TODO throw exception
        }
        // TODO if wallet doesn't exist create it
        createWalletIfDoesNotExist(wallet_id);
        // TODO if no stock in the wallet fail with 400
        // TODO sell stock and add event
    }

    private void createWalletIfDoesNotExist(String wallet_id) {
        if (walletRepository.findById(wallet_id).isEmpty()) {
            walletRepository.save(
                    createEmptyWalletWithId(wallet_id)
            );
        }
    }

    private Wallet createEmptyWalletWithId(String wallet_id) {
        Wallet wallet = new Wallet();
        wallet.setId(wallet_id);
        wallet.setStocks(new ArrayList<>());
        return wallet;
    }

    private boolean checkIfStockInWallet(String wallet_id, String stock_name) {

    }
}
