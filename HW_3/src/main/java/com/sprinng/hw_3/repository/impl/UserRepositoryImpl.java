package com.sprinng.hw_3.repository.impl;

import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

  @Override
  public void add(User user) throws RepositoryExeption {}

  @Override
  public void update(User user) throws RepositoryExeption {}

  @Override
  public User getByEmailAndPassword(String email, String password) throws RepositoryExeption {
    return null;
  }

  @Override
  public User getByEmail(String email) throws RepositoryExeption {
    return null;
  }

  @Override
  public List<User> getAll() throws RepositoryExeption {
    return null;
  }

  @Override
  public void changeLockStatus(long userId, boolean lockStatus) throws RepositoryExeption {}

  @Override
  public void delete(String email) throws RepositoryExeption {}
}
