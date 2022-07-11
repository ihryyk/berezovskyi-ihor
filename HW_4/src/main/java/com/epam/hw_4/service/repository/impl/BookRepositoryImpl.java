package com.epam.hw_4.service.repository.impl;

import com.epam.hw_4.model.entity.Book;
import com.epam.hw_4.service.exeption.RepositoryException;
import com.epam.hw_4.service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {

  @Override
  public Book getById(long bookId) throws RepositoryException {
    return null;
  }

  @Override
  public List<Book> getByAuthor(String author) throws RepositoryException {
    return null;
  }

  @Override
  public List<Book> getAll() throws RepositoryException {
    return null;
  }

  @Override
  public List<Book> getByTitle(String title) throws RepositoryException {
    return null;
  }

  @Override
  public void changeNumber(long bookId, int newNumber) throws RepositoryException {}

  @Override
  public void update(Book updatedBook) throws RepositoryException {}

  @Override
  public void delete(long id) throws RepositoryException {}

  @Override
  public void add(Book book) throws RepositoryException {}
}
