package com.github.gaskapiotr.stockmarketsim.bank.manager;

import com.github.gaskapiotr.stockmarketsim.bank.BankExternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankInternalAPI;
import com.github.gaskapiotr.stockmarketsim.bank.BankStockQuantityIsZeroException;
import com.github.gaskapiotr.stockmarketsim.bank.StocksDTO;
import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import com.github.gaskapiotr.stockmarketsim.bank.mapper.BankMapper;
import com.github.gaskapiotr.stockmarketsim.bank.repository.BankRepository;
import com.github.gaskapiotr.stockmarketsim.gateway.BankStockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    // TODO check if stock is more than 0
    public void setStocks(StocksDTO stocksDTO) {
        deleteAllBankStocks();
        List<BankStock> bankStocks = stocksDTO.stocks().stream()
                .map(bankMapper::toEntity)
                .toList();
        bankRepository.saveAll(bankStocks);
    }

    private void deleteAllBankStocks() {
        bankRepository.deleteAll();
    }


    @Override
    public boolean doesStockExist(String name) {
        return bankRepository.findById(name).isPresent();
    }

    @Override
    @Transactional
    public void increaseStock(String stock_name) {
        BankStock bankStock = bankRepository.findByIdWithLock(stock_name).orElseGet(
                () -> createBankStockWithQuantityZero(stock_name)
        );
        increaseStockQuantityByOne(bankStock);
        bankRepository.save(bankStock);
    }

    private BankStock createBankStockWithQuantityZero(String stock_name) {
        return createBankStock(stock_name, 0);
    }

    private BankStock createBankStock(String stock_name, int quantity) {
        BankStock bankStock = new BankStock();
        bankStock.setName(stock_name);
        bankStock.setQuantity(quantity);
        return bankStock;
    }

    private void increaseStockQuantityByOne(BankStock bankStock) {
        bankStock.setQuantity(bankStock.getQuantity() + 1);
    }

    @Override
    @Transactional
    public void decreaseStock(String stock_name) {
        BankStock bankStock = bankRepository.findByIdWithLock(stock_name).orElseThrow(
                () -> new BankStockNotFoundException(stock_name)
        );
        if (bankStock.getQuantity() == 0) {
            throw new BankStockQuantityIsZeroException(stock_name);
        }
        decreaseStockQuantityByOne(bankStock);
        bankRepository.save(bankStock);
    }

    private void decreaseStockQuantityByOne(BankStock bankStock) {
        bankStock.setQuantity(bankStock.getQuantity() - 1);
    }
}
