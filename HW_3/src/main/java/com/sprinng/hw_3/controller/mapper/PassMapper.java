package com.sprinng.hw_3.controller.mapper;

import com.sprinng.hw_3.controller.dto.OrderDTO;
import com.sprinng.hw_3.controller.dto.PassDTO;
import com.sprinng.hw_3.model.entity.Order;
import com.sprinng.hw_3.model.entity.Pass;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassMapper {
    PassMapper INSTANCE = Mappers.getMapper(PassMapper.class);
    Pass mapToEntity(PassDTO passDTO);
    PassDTO mapToDto(Pass pass);
}
