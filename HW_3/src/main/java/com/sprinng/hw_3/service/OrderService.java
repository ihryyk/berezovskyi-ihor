package com.sprinng.hw_3.service;


import com.sprinng.hw_3.controller.dto.OrderDTO;
import com.sprinng.hw_3.model.enums.OrderStatus;
import com.sprinng.hw_3.service.exeption.ServiceException;

import java.util.List;

public interface OrderService {
    public void update(OrderDTO orderDTO);

    public List<OrderDTO> getAllByUserId(long userID) throws ServiceException;

    public OrderDTO getById(long orderId) throws ServiceException;

    public List<OrderDTO> getAll() throws ServiceException;

    public void create(OrderDTO orderDTO) throws ServiceException;

    public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException;
}
