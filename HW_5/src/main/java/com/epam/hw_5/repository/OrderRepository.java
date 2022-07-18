package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.Order;
import com.epam.hw_5.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  public List<Order> findAllByUser(User user);
}
