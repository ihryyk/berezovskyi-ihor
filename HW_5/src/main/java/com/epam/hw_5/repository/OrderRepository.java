package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("from Order where user.id=?1")
  public List<Order> getAllByUserId(long userId);
}
