package com.sprinng.hw_3.controller.mapper;

import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.controller.dto.OrderDTO;
import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order mapToEntity(OrderDTO orderDTO);
    OrderDTO mapToDto(Order order);
}
