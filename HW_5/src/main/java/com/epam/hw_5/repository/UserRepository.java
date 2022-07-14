package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findUserByEmailAddressAndPassword(String email, String password);

  public Optional<User> findUserByEmailAddress(String email);

  public void deleteByEmailAddress(String email);
}
