package com.github.gaskapiotr.stockmarketsim.bank.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankStockDTO;
import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import com.github.gaskapiotr.stockmarketsim.bank.mapper.BankMapper;
import com.github.gaskapiotr.stockmarketsim.bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankManager implements BankExternalAPI {
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    public List<BankStockDTO> getAllStocks() {
        return bankRepository.findAll().stream()
                .map(bankMapper::toDto);
    }

    @Transactional
    @Override
    public void addStocks(List<BankStockDTO> stocks) {
        List<BankStock> bankStocks = stocks.stream()
                .map(bankMapper::toEntity)
                .toList();
        bankRepository.saveAll(bankStocks);
    }
}
