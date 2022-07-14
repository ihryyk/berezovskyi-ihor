package com.epam.hw_4.repository;

import com.epam.hw_4.model.entity.User;
import com.epam.hw_4.model.enums.LockStatus;
import com.epam.hw_4.service.exeption.RepositoryException;

import java.util.List;

public interface UserRepository {

  public void add(User user) throws RepositoryException;

  public void update(User user) throws RepositoryException;

  public User getByEmailAndPassword(String email, String password) throws RepositoryException;

  public User getByEmail(String email) throws RepositoryException;

  public List<User> getAll() throws RepositoryException;

  public void changeLockStatus(long userId, LockStatus lockStatus) throws RepositoryException;

  public void delete(String email) throws RepositoryException;
}
