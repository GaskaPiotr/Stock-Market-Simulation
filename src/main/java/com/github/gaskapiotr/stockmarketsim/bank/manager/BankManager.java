package com.github.gaskapiotr.stockmarketsim.bank.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.StocksDTO;
import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import com.github.gaskapiotr.stockmarketsim.bank.mapper.BankMapper;
import com.github.gaskapiotr.stockmarketsim.bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankManager implements BankExternalAPI, BankInternalAPI {
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    public StocksDTO getAllStocks() {
        return new StocksDTO(bankRepository.findAll().stream()
                .map(bankMapper::toDto)
                .toList());
    }

    // TODO change to setStocks?
    @Transactional
    @Override
    public void addStocks(StocksDTO stocksDTO) {
        List<BankStock> bankStocks = stocksDTO.stocks().stream()
                .map(bankMapper::toEntity)
                .toList();
        bankRepository.saveAll(bankStocks);
    }

    @Override
    public boolean doesStockExist(String name) {
        return bankRepository.findById(name).isPresent();
    }

    void addStock(String stock_name) {
        Optional<BankStock> bankStockOptional = bankRepository.findById(stock_name);

        if (bankStockOptional.isPresent()) {
            BankStock bankStock = bankStockOptional.get();
            bankStock.setQuantity(bankStock.getQuantity() + 1);
            bankRepository.save(bankStock);
        } else {
            BankStock bankStock = createBankStock(stock_name, 1);
            bankRepository.save(bankStock);
        }
    }

    private BankStock createBankStock(String stock_name, int quantity) {
        BankStock bankStock = new BankStock();
        bankStock.setName(stock_name);
        bankStock.setQuantity(quantity);
        return bankStock;
    }


}
