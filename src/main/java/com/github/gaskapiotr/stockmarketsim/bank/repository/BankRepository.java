package com.github.gaskapiotr.stockmarketsim.bank.repository;

import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankStock, String> {
}
