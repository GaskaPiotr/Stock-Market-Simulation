package com.github.gaskapiotr.stockmarketsim.wallet.repository;

import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.WalletStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
}
