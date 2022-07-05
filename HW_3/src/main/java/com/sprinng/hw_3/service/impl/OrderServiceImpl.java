package com.sprinng.hw_3.service.impl;


import com.sprinng.hw_3.controller.dto.OrderDTO;
import com.sprinng.hw_3.controller.mapper.OrderMapper;
import com.sprinng.hw_3.model.entity.Order;
import com.sprinng.hw_3.model.enums.OrderStatus;
import com.sprinng.hw_3.service.OrderService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import com.sprinng.hw_3.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void update(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.mapToEntity(orderDTO);
        orderRepository.update(order);
        log.info("update order");
    }

    @Override
    public List<OrderDTO> getAllByUserId(long userId) throws ServiceException {
        log.info("get all orders by user id {}", userId);
        return orderRepository.getAllByUserId(userId).stream().map(OrderMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public OrderDTO getById(long id) throws ServiceException {
        Order order = orderRepository.getById(id);

        if (order == null) {
            throw new ServiceException(format("ORDER with id %o not found", id));
        }
        log.info("get order by id {}", id);
        return OrderMapper.INSTANCE.mapToDto(order);
    }

    @Override
    public List<OrderDTO> getAll() throws ServiceException {
        log.info("get all orders ");
        return orderRepository.getAll().stream().map(OrderMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public void create(OrderDTO orderDTO) throws ServiceException {
        log.info("create new order");
        Order order = OrderMapper.INSTANCE.mapToEntity(orderDTO);
        orderRepository.create(order);
    }

    @Override
    public void changeOrderStatus(long id, OrderStatus orderStatus) throws ServiceException {
        log.info("change order status to {} in order with id {}", orderStatus, id);
        orderRepository.changeOrderStatus(id, orderStatus);
    }
}
