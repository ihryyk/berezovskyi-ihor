package com.epam.hw_5.service;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.service.exeption.ServiceException;

import java.util.List;

public interface BookService {
  public BookDTO getById(long bookId) throws ServiceException;

  public List<BookDTO> getByAuthor(String author, int page, int size) throws ServiceException;

  public List<BookDTO> getAll(int page, int size) throws ServiceException;

  public List<BookDTO> getByTitle(String title, int page, int size) throws ServiceException;

  public void changeNumber(long bookId, int newNumber) throws ServiceException;

  public BookDTO save(BookDTO updatedBookDTO) throws ServiceException;

  public void delete(long id) throws ServiceException;

  List<BookDTO> sortBooks(String sortBy, int size, int page) throws ServiceException;
}
