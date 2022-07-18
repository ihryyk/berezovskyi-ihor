package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.controller.mapper.BookMapper;
import com.epam.hw_5.model.entity.Book;
import com.epam.hw_5.repository.BookRepository;
import com.epam.hw_5.service.BookService;
import com.epam.hw_5.service.exeption.RepositoryException;
import com.epam.hw_5.service.exeption.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  @Transactional(readOnly = true)
  public BookDTO getById(long id) throws ServiceException {

    Book book =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("Book with id " + id + " not found"));
    log.info("get book by id {}", id);
    return BookMapper.INSTANCE.mapToDto(book);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> getByAuthor(String author, int page, int size) {
    log.info("get books by author {}", author);
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAllByAuthor(author, pageable).stream()
        .map(BookMapper.INSTANCE::mapToDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> getAll(int page, int size) {
    log.info("get all books");
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAll(pageable).stream().map(BookMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> getByTitle(String title, int page, int size) {
    log.info("get books by title {}", title);
    Pageable pageable = PageRequest.of(page, size);
    return bookRepository.findAllByTitle(title, pageable).stream()
        .map(BookMapper.INSTANCE::mapToDto)
        .toList();
  }

  @Override
  public void changeNumber(long id, int newNumber) {
    log.info("change the number of books to {} with id {}", newNumber, id);
    bookRepository.changeNumber(id, newNumber);
  }

  @Override
  public void save(BookDTO updatedBookDTO) {
    log.info("update book with title {}", updatedBookDTO.getTitle());
    Book updatedBook = BookMapper.INSTANCE.mapToEntity(updatedBookDTO);
    bookRepository.save(updatedBook);
  }

  @Override
  @Transactional
  public void delete(long id) throws ServiceException {
    Book book =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("Book with id " + id + " not found"));
    bookRepository.delete(book);
    log.info("delete book by id {}", id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> sortBooks(String sortBy, int size, int page) {
    Pageable sortedBy = PageRequest.of(page, size, Sort.by(sortBy));
    return bookRepository.findAll(sortedBy).stream().map(BookMapper.INSTANCE::mapToDto).toList();
  }
}
