package com.sprinng.hw_3.repository;

import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.model.entity.Pass;
import com.sprinng.hw_3.model.enums.PassStatus;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;

import java.util.List;

public interface PassRepository {

  public void changePassStatus(long passId, PassStatus passStatus) throws RepositoryExeption;

  public List<Book> getBooksById(long passId) throws RepositoryExeption;

  public void create(Pass pass) throws RepositoryExeption;

  public List<Pass> getAll() throws RepositoryExeption;

  public List<Pass> getAllByUserId(long userID) throws RepositoryExeption;

  public void update(Pass pass) throws RepositoryExeption;

  public void updatePenalty(long id, int penalty) throws RepositoryExeption;
}
