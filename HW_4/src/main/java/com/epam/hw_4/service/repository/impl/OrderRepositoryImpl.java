package com.epam.hw_4.service.repository.impl;


import com.epam.hw_4.model.entity.Order;
import com.epam.hw_4.model.enums.OrderStatus;
import com.epam.hw_4.service.exeption.RepositoryException;
import com.epam.hw_4.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public void update(Order order) throws RepositoryException {

    }

    @Override
    public List<Order> getAllByUserId(long userID) throws RepositoryException {
        return null;
    }

    @Override
    public Order getById(long orderId) throws RepositoryException {
        return null;
    }

    @Override
    public List<Order> getAll() throws RepositoryException {
        return null;
    }

    @Override
    public void create(Order order) throws RepositoryException {

    }

    @Override
    public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws RepositoryException {

    }
}
