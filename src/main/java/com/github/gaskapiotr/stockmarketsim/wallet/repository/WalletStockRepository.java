package com.github.gaskapiotr.stockmarketsim.wallet.repository;

import com.github.gaskapiotr.stockmarketsim.wallet.entity.WalletStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletStockRepository extends JpaRepository<WalletStock, Long> {
    Optional<WalletStock> findByNameAndWalletId(String name, String wallet_id);
}
