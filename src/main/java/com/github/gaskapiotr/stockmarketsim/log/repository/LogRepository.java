package com.github.gaskapiotr.stockmarketsim.log.repository;

import com.github.gaskapiotr.stockmarketsim.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
