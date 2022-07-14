package com.epam.hw_4.repository;

import com.epam.hw_4.model.entity.Book;
import com.epam.hw_4.model.entity.Pass;
import com.epam.hw_4.model.enums.PassStatus;
import com.epam.hw_4.service.exeption.RepositoryException;

import java.util.List;

public interface PassRepository {
  public void changePassStatus(long passId, PassStatus passStatus) throws RepositoryException;

  public List<Book> getBooksById(long passId) throws RepositoryException;

  public void create(Pass pass) throws RepositoryException;

  public List<Pass> getAll() throws RepositoryException;

  public List<Pass> getAllByUserId(long userID) throws RepositoryException;

  public void update(Pass pass) throws RepositoryException;

  public void updatePenalty(long id, int penalty) throws RepositoryException;
}
