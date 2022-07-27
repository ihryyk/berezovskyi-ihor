package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.User;
import com.epam.hw_5.service.exeption.RepositoryException;

import java.util.List;

public interface JdbcLibrarianRepository {
  User save(User librarian) throws RepositoryException;

  List<User> getAll() throws RepositoryException;
}
