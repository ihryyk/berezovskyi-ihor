package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.OrderDTO;
import com.epam.hw_5.controller.mapper.OrderMapper;
import com.epam.hw_5.model.entity.Order;
import com.epam.hw_5.model.enums.OrderStatus;
import com.epam.hw_5.repository.OrderRepository;
import com.epam.hw_5.repository.UserRepository;
import com.epam.hw_5.service.OrderService;
import com.epam.hw_5.service.exeption.RepositoryException;
import com.epam.hw_5.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  @Override
  public OrderDTO save(OrderDTO orderDTO) {
    Order order = OrderMapper.INSTANCE.mapToEntity(orderDTO);
    log.info("save order");
    return OrderMapper.INSTANCE.mapToDto(orderRepository.save(order));
  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderDTO> getAllByUserId(long userId) throws RepositoryException {
    log.info("get all orders by user id {}", userId);
    try {
      userRepository
          .findById(userId)
          .orElseThrow(() -> new RepositoryException("User with id " + userId + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    return orderRepository.getAllByUserId(userId).stream()
        .map(OrderMapper.INSTANCE::mapToDto)
        .toList();
  }

  @Override
  @Transactional
  public OrderDTO getById(long id) throws RepositoryException {
    Order order = null;
    try {
      order =
          orderRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("Order with id " + id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    log.info("get order by id {}", id);
    return OrderMapper.INSTANCE.mapToDto(order);
  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderDTO> getAll() {
    log.info("get all orders ");
    return orderRepository.findAll().stream().map(OrderMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional
  public void changeOrderStatus(long id, OrderStatus orderStatus) throws RepositoryException {
    Order order = null;
    try {
      order =
          orderRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("Order with id " + id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    order.setOrderStatus(orderStatus);
    orderRepository.save(order);
    log.info("change order status to {} in order with id {}", orderStatus, id);
  }
}
