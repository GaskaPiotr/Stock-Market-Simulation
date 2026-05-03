package com.github.gaskapiotr.stockmarketsim.log.mapper;

import com.github.gaskapiotr.stockmarketsim.log.LogDTO;
import com.github.gaskapiotr.stockmarketsim.log.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LogMapper {
    LogDTO toDTO(Log entity);
}
