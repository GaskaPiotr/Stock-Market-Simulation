package com.github.gaskapiotr.stockmarketsim.wallet.repository;

import com.github.gaskapiotr.stockmarketsim.wallet.entity.WalletStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletStockRepository extends JpaRepository<WalletStock, Long> {
    Optional<WalletStock> findByNameAndWalletId(String name, String wallet_id);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ws FROM WalletStock ws WHERE ws.name = :stock_name AND ws.wallet.id = :wallet_id")
    Optional<WalletStock> findByNameAndWalletIdWithLock(String stock_name, String wallet_id);
}
