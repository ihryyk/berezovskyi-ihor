package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {

  @Query("from Pass where order.user.id =?1")
  public List<Pass> getAllByUserId(long userId);
}
