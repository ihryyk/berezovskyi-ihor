package com.epam.hw_4.controller.mapper;

import com.epam.hw_4.controller.dto.OrderDTO;
import com.epam.hw_4.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  Order mapToEntity(OrderDTO orderDTO);

  OrderDTO mapToDto(Order order);
}
