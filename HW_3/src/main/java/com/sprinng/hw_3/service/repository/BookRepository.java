package com.sprinng.hw_3.service.repository;

import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;

import java.util.List;

public interface BookRepository {

  public Book getById(long bookId) throws RepositoryExeption;

  public List<Book> getByAuthor(String author) throws RepositoryExeption;

  public List<Book> getAll() throws RepositoryExeption;

  public List<Book> getByTitle(String title) throws RepositoryExeption;

  public void changeNumber(long bookId, int newNumber) throws RepositoryExeption;

  public void update(Book updatedBook) throws RepositoryExeption;

  public void delete(long id) throws RepositoryExeption;

  public void add(Book book) throws RepositoryExeption;
}
