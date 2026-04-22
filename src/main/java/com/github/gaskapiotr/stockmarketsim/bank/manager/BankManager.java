package com.github.gaskapiotr.stockmarketsim.bank.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankStockDTO;
import com.github.gaskapiotr.stockmarketsim.bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankManager implements BankExternalAPI {
    private final BankRepository bankRepository;

    @Override
    public void addStocks(List<BankStockDTO> stocks) {
        // TODO change dto to entity then add to repo
    }
}
