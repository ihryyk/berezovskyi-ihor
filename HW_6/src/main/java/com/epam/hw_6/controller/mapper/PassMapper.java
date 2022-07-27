package com.epam.hw_6.controller.mapper;

import com.epam.hw_6.controller.dto.PassDTO;
import com.epam.hw_6.model.entity.Pass;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassMapper {
  PassMapper INSTANCE = Mappers.getMapper(PassMapper.class);

  Pass mapToEntity(PassDTO passDTO);

  PassDTO mapToDto(Pass pass);
}
