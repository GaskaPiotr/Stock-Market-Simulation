package com.github.gaskapiotr.stockmarketsim.wallet.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.SellStockEvent;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletExternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletInternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.WalletStock;
import com.github.gaskapiotr.stockmarketsim.wallet.repository.WalletRepository;
import com.github.gaskapiotr.stockmarketsim.wallet.repository.WalletStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletManager implements WalletExternalAPI, WalletInternalAPI {
    private final WalletRepository walletRepository;
    private final WalletStockRepository walletStockRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    // TODO catch exception if already added
    public void addWalletIfDoesNotExist(String wallet_id) {
        if (walletRepository.findById(wallet_id).isEmpty()) {
            walletRepository.save(
                    getEmptyWalletWithId(wallet_id)
            );
        }
    }

    private Wallet getEmptyWalletWithId(String wallet_id) {
        Wallet wallet = new Wallet();
        wallet.setId(wallet_id);
        wallet.setStocks(new ArrayList<>());
        return wallet;
    }

    @Override
    @Transactional
    public void sellStock(String wallet_id, String stock_name) {
        WalletStock walletStock = getStockInWallet(wallet_id, stock_name).orElseThrow(
            // TODO throw exception;
        );
        decreaseStock(walletStock);
        publishSellStockEvent(wallet_id, stock_name);
    }

    // TODO add pessimistic lock
    private Optional<WalletStock> getStockInWallet(String wallet_id, String stock_name) {
        return walletStockRepository.findByNameAndWalletId(stock_name, wallet_id);
    }

    private void decreaseStock(WalletStock walletStock) {
        decreaseStockQuantityByOne(walletStock);
        if (walletStock.getQuantity() <= 0) {
            walletStockRepository.delete(walletStock);
        } else {
            walletStockRepository.save(walletStock);
        }
    }

    private void decreaseStockQuantityByOne(WalletStock walletStock) {
        walletStock.setQuantity(walletStock.getQuantity() - 1);
    }

    private void publishSellStockEvent(String wallet_id, String stock_name) {
        eventPublisher.publishEvent(new SellStockEvent(wallet_id, stock_name));
    }
}
