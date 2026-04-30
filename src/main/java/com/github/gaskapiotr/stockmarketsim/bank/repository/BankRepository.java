package com.github.gaskapiotr.stockmarketsim.bank.repository;

import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankStock, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT bs FROM BankStock bs WHERE bs.name = :stock_name")
    Optional<BankStock> findByIdWithLock(String stock_name);
}
