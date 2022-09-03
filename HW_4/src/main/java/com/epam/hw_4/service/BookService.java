package com.epam.hw_4.service;

import com.epam.hw_4.controller.dto.BookDTO;
import com.epam.hw_4.service.exeption.ServiceException;

import java.util.List;

public interface BookService {
  public BookDTO getById(long bookId) throws ServiceException;

  public List<BookDTO> getByAuthor(String author) throws ServiceException;

  public List<BookDTO> getAll() throws ServiceException;

  public List<BookDTO> getByTitle(String title) throws ServiceException;

  public void changeNumber(long bookId, int newNumber) throws ServiceException;

  public void update(BookDTO updatedBookDTO) throws ServiceException;

  public void delete(long id) throws ServiceException;

  public void add(BookDTO bookDTO) throws ServiceException;

  List<BookDTO> sortBooks(String sortBy) throws ServiceException;
}
