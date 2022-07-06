package com.epam.hw_4.service.repository;


import com.epam.hw_4.model.entity.Book;
import com.epam.hw_4.service.exeption.RepositoryException;

import java.util.List;

public interface BookRepository {

    public Book getById(long bookId) throws RepositoryException;

    public List<Book> getByAuthor(String author) throws RepositoryException;

    public List<Book> getAll() throws RepositoryException;

    public List<Book> getByTitle(String title) throws RepositoryException;

    public void changeNumber(long bookId, int newNumber) throws RepositoryException;

    public void update(Book updatedBook) throws RepositoryException;

    public void delete(long id) throws RepositoryException;

    public void add(Book book) throws RepositoryException;

}
