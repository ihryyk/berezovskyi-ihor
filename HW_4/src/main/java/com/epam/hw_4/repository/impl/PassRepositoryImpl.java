package com.epam.hw_4.repository.impl;

import com.epam.hw_4.model.entity.Book;
import com.epam.hw_4.model.entity.Pass;
import com.epam.hw_4.model.enums.PassStatus;
import com.epam.hw_4.repository.PassRepository;
import com.epam.hw_4.service.exeption.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class PassRepositoryImpl implements PassRepository {

  @Override
  public void changePassStatus(long passId, PassStatus passStatus) throws RepositoryException {}

  @Override
  public List<Book> getBooksById(long passId) throws RepositoryException {
    return null;
  }

  @Override
  public void create(Pass pass) throws RepositoryException {}

  @Override
  public List<Pass> getAll() throws RepositoryException {
    return null;
  }

  @Override
  public List<Pass> getAllByUserId(long userID) throws RepositoryException {
    return null;
  }

  @Override
  public void update(Pass pass) throws RepositoryException {}

  @Override
  public void updatePenalty(long id, int penalty) throws RepositoryException {}
}
