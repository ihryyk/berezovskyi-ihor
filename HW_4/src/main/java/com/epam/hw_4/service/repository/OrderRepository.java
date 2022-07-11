package com.epam.hw_4.service.repository;

import com.epam.hw_4.model.entity.Order;
import com.epam.hw_4.model.enums.OrderStatus;
import com.epam.hw_4.service.exeption.RepositoryException;

import java.util.List;

public interface OrderRepository {

  public void update(Order order) throws RepositoryException;

  public List<Order> getAllByUserId(long userID) throws RepositoryException;

  public Order getById(long orderId) throws RepositoryException;

  public List<Order> getAll() throws RepositoryException;

  public void create(Order order) throws RepositoryException;

  public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws RepositoryException;
}
