package com.github.gaskapiotr.stockmarketsim.wallet.repository;

import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
}
