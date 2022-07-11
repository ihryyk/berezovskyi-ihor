package com.sprinng.hw_3.service.repository;

import com.sprinng.hw_3.model.entity.Order;
import com.sprinng.hw_3.model.enums.OrderStatus;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;

import java.util.List;

public interface OrderRepository {

  public void update(Order order) throws RepositoryExeption;

  public List<Order> getAllByUserId(long userID) throws RepositoryExeption;

  public Order getById(long orderId) throws RepositoryExeption;

  public List<Order> getAll() throws RepositoryExeption;

  public void create(Order order) throws RepositoryExeption;

  public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws RepositoryExeption;
}
