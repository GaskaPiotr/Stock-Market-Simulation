package com.github.gaskapiotr.stockmarketsim.bank.mapper;

import com.github.gaskapiotr.stockmarketsim.bank.BankStockDTO;
import com.github.gaskapiotr.stockmarketsim.bank.entity.BankStock;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankMapper {
    BankStock toEntity(BankStockDTO dto);
}
