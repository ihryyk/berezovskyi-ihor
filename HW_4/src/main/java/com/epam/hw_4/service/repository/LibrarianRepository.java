package com.epam.hw_4.service.repository;

import com.epam.hw_4.model.entity.User;
import com.epam.hw_4.service.exeption.RepositoryException;

import java.util.List;

public interface LibrarianRepository {
  public void create(User librarian) throws RepositoryException;

  public List<User> getAll() throws RepositoryException;
}
