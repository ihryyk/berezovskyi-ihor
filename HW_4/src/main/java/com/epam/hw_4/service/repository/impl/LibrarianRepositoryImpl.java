package com.epam.hw_4.service.repository.impl;

import com.epam.hw_4.model.entity.User;
import com.epam.hw_4.service.exeption.RepositoryException;
import com.epam.hw_4.service.repository.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class LibrarianRepositoryImpl implements LibrarianRepository {

  @Override
  public void create(User librarian) throws RepositoryException {}

  @Override
  public List<User> getAll() throws RepositoryException {
    return null;
  }
}
