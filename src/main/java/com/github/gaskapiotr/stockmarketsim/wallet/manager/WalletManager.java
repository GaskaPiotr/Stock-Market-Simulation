package com.github.gaskapiotr.stockmarketsim.wallet.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletExternalAPI;
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
public class WalletManager implements WalletExternalAPI {
    private final WalletRepository walletRepository;
    private final WalletStockRepository walletStockRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final BankInternalAPI bankInternalAPI;

    @Override
    @Transactional
    public void sellStock(String wallet_id, String stock_name) {
        prepareBeforeTransaction(wallet_id, stock_name);
        // TODO if no stock in the wallet fail with 400
        WalletStock walletStock = getStockInWallet(wallet_id, stock_name).orElseThrow(
                // TODO throw exception;
        );
        sellOneStock(walletStock);
    }

    private void prepareBeforeTransaction(String wallet_id, String stock_name) {
        // TODO if does not exist fail with 400
        if (!bankInternalAPI.doesStockExist(stock_name)) {
            // TODO throw exception `
        }
        createWalletIfDoesNotExist(wallet_id);
    }

    // TODO catch exception if already added
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

    // TODO add pessimistic lock
    private Optional<WalletStock> getStockInWallet(String wallet_id, String stock_name) {
        return walletStockRepository.findByNameAndWalletId(stock_name, wallet_id);
    }

    private void sellOneStock(WalletStock walletStock) {
        decreaseStockQuantityByOne(walletStock);
        if (walletStock.getQuantity() <= 0) {
            walletStockRepository.delete(walletStock);
        } else {
            walletStockRepository.save(walletStock);
        }
        // TODO add sell stock event
    }

    private void decreaseStockQuantityByOne(WalletStock walletStock) {
        walletStock.setQuantity(walletStock.getQuantity() - 1);
    }
}
