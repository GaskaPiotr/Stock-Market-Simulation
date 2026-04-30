package com.github.gaskapiotr.stockmarketsim.wallet.manager;

import com.github.gaskapiotr.stockmarketsim.wallet.*;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.WalletStock;
import com.github.gaskapiotr.stockmarketsim.wallet.mapper.WalletMapper;
import com.github.gaskapiotr.stockmarketsim.wallet.repository.WalletRepository;
import com.github.gaskapiotr.stockmarketsim.wallet.repository.WalletStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletManager implements WalletExternalAPI, WalletInternalAPI {
    private final WalletRepository walletRepository;
    private final WalletStockRepository walletStockRepository;
    private final WalletMapper walletMapper;

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
        WalletStock walletStock = getStockInWalletWithLock(wallet_id, stock_name).orElseThrow(
                () -> new WalletStockNotFoundException(wallet_id, stock_name)
        );
        decreaseStock(walletStock);
    }

    private Optional<WalletStock> getStockInWalletWithLock(String wallet_id, String stock_name) {
        return walletStockRepository.findByNameAndWalletIdWithLock(stock_name, wallet_id);
    }

    private void decreaseStock(WalletStock walletStock) {
        decreaseStockQuantityByOne(walletStock);
        if (walletStock.getQuantity() == 0) {
            walletStockRepository.delete(walletStock);
        } else {
            walletStockRepository.save(walletStock);
        }
    }

    private void decreaseStockQuantityByOne(WalletStock walletStock) {
        walletStock.setQuantity(walletStock.getQuantity() - 1);
    }

    @Override
    public WalletDTO getWallet(String wallet_id) {
        return walletMapper.toDTO(
                walletRepository.findById(wallet_id).orElseThrow(
                        () -> new WalletNotFoundException(wallet_id)
                ));
    }

    @Override
    @Transactional
    public void increaseStock(String wallet_id, String stock_name) {
        Optional<WalletStock> walletStockOptional = getStockInWallet(wallet_id, stock_name);
        WalletStock walletStock;
        if (walletStockOptional.isPresent()) {
            walletStock = walletStockOptional.get();
            walletStock.setQuantity(walletStock.getQuantity() + 1);
        } else {
            walletStock = new WalletStock();
            walletStock.setName(stock_name);
            walletStock.setQuantity(1);
            Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(
                    // TODO throw exception
            );
            walletStock.setWallet(wallet);
        }
        walletStockRepository.save(walletStock);
    }


    private Optional<WalletStock> getStockInWallet(String wallet_id, String stock_name) {
        return walletStockRepository.findByNameAndWalletId(stock_name, wallet_id);
    }

    @Override
    public int getWalletStockQuantity(String wallet_id, String stock_name) {
        Optional<WalletStock> walletStock =  getStockInWallet(wallet_id, stock_name);
        return walletStock.map(WalletStock::getQuantity).orElse(0);
    }

}
