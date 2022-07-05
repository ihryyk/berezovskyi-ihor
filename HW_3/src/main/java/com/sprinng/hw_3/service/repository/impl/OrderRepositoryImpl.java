package com.sprinng.hw_3.service.repository.impl;


import com.sprinng.hw_3.model.entity.Order;
import com.sprinng.hw_3.model.enums.OrderStatus;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.service.repository.OrderRepository;
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
    public void update(Order order) throws RepositoryExeption {

    }

    @Override
    public List<Order> getAllByUserId(long userID) throws RepositoryExeption {
        return null;
    }

    @Override
    public Order getById(long orderId) throws RepositoryExeption {
        return null;
    }

    @Override
    public List<Order> getAll() throws RepositoryExeption {
        return null;
    }

    @Override
    public void create(Order order) throws RepositoryExeption {

    }

    @Override
    public void changeOrderStatus(long orderId, OrderStatus orderStatus) throws RepositoryExeption {

    }
}
