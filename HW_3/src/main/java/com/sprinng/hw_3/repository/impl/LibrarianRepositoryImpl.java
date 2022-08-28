package com.sprinng.hw_3.repository.impl;

import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.repository.LibrarianRepository;
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
  public void create(User librarian) throws RepositoryExeption {}

  @Override
  public List<User> getAll() throws RepositoryExeption {
    return null;
  }
}
