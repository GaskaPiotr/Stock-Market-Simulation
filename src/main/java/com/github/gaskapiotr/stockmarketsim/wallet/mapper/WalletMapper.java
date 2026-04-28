package com.github.gaskapiotr.stockmarketsim.wallet.mapper;

import com.github.gaskapiotr.stockmarketsim.wallet.WalletDTO;
import com.github.gaskapiotr.stockmarketsim.wallet.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WalletMapper {
    WalletDTO toDTO(Wallet entity);
}
