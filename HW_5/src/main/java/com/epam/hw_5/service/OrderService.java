package com.epam.hw_5.service;

import com.epam.hw_5.controller.dto.OrderDTO;
import com.epam.hw_5.model.enums.OrderStatus;
import com.epam.hw_5.service.exeption.ServiceException;

import java.util.List;

public interface OrderService {
  public OrderDTO save(OrderDTO orderDTO);

  public List<OrderDTO> getAllByUserId(long userID) throws ServiceException;

  public OrderDTO getById(long orderId) throws ServiceException;

  public List<OrderDTO> getAll() throws ServiceException;

  public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException;
}
