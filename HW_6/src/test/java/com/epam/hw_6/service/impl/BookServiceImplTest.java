package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.BookDTO;
import com.epam.hw_6.controller.mapper.BookMapper;
import com.epam.hw_6.model.entity.Book;
import com.epam.hw_6.repository.BookRepository;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.hw_6.util.BookTestDataUtil.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @InjectMocks private BookServiceImpl bookService;
  @Mock private BookRepository bookRepository;

  @Test
  void getById() {
    Book expectedBook = getBook();
    when(bookRepository.findById(MOCK_BOOK_ID)).thenReturn(Optional.of(expectedBook));
    BookDTO actualBook = bookService.getById(MOCK_BOOK_ID);
    assertThat(actualBook.getTitle(), equalTo(MOCK_TITLE));
  }

  @Test
  void getByIdWithRepositoryException() {
    doThrow(RepositoryException.class).when(bookRepository).findById(MOCK_BOOK_ID);
    assertThrows(ServiceException.class, () -> bookService.getById(MOCK_BOOK_ID));
  }

  @Test
  void getByAuthor() {
    Book book = getBook();
    when(bookRepository.findAllByAuthor(MOCK_AUTHOR, getPageable()))
        .thenReturn(Collections.singletonList(book));
    List<BookDTO> actualBook = bookService.getByAuthor(MOCK_AUTHOR, MOCK_PAGE, MOCK_SIZE);
    assertThat(actualBook, hasSize(1));
  }

  @Test
  void getAll() {
    Book book = getBook();
    Page<Book> allBooks = new PageImpl<>(List.of(book));
    when(bookRepository.findAll(getPageable())).thenReturn(allBooks);
    List<BookDTO> actualBooks = bookService.getAll(MOCK_PAGE, MOCK_SIZE);
    assertThat(actualBooks, hasSize(1));
  }

  @Test
  void getByTitle() {
    Book book = getBook();
    when(bookRepository.findAllByTitle(MOCK_TITLE, getPageable()))
        .thenReturn(Collections.singletonList(book));
    List<BookDTO> actualBook = bookService.getByTitle(MOCK_TITLE, MOCK_PAGE, MOCK_SIZE);
    assertThat(actualBook, hasSize(1));
  }

  @Test
  void changeNumber() {
    Book book = getBook();
    int newNumber = 2;
    book.setNumber(newNumber);
    when(bookRepository.findById(MOCK_BOOK_ID)).thenReturn(Optional.of(book));
    doNothing().when(bookRepository).changeNumber(MOCK_BOOK_ID, newNumber);
    bookService.changeNumber(MOCK_BOOK_ID, newNumber);
    verify(bookRepository, times(1)).changeNumber(MOCK_BOOK_ID, newNumber);
  }

  @Test
  void changeNumberWithRepositoryException() {
    int newNumber = 2;
    doThrow(RepositoryException.class).when(bookRepository).findById(MOCK_BOOK_ID);
    assertThrows(ServiceException.class, () -> bookService.changeNumber(MOCK_BOOK_ID, newNumber));
  }

  @Test
  void save() {
    Book expectedBook = getBook();
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookRepository.save(any())).thenReturn(expectedBook);
    BookDTO savedBook = bookService.save(bookDTO);
    assertThat(
        savedBook,
        allOf(
            hasProperty("title", CoreMatchers.equalTo(expectedBook.getTitle())),
            hasProperty("author", CoreMatchers.equalTo(expectedBook.getAuthor()))));
  }

  @Test
  void delete() {
    Book book = getBook();
    when(bookRepository.findById(MOCK_BOOK_ID)).thenReturn(Optional.of(book));
    doNothing().when(bookRepository).setDeleteTrue(MOCK_BOOK_ID);
    bookService.delete(MOCK_BOOK_ID);
    verify(bookRepository, times(1)).setDeleteTrue(MOCK_BOOK_ID);
  }

  @Test
  void deleteWithRepositoryException() {
    doThrow(RepositoryException.class).when(bookRepository).findById(MOCK_BOOK_ID);
    assertThrows(ServiceException.class, () -> bookService.delete(MOCK_BOOK_ID));
  }

  @Test
  void sortBooks() {
    Book book = getBook();
    Page<Book> allBooks = new PageImpl<>(List.of(book));
    String sortBy = "title";
    Pageable pageable = PageRequest.of(MOCK_SIZE, MOCK_PAGE, Sort.by(sortBy));
    when(bookRepository.findAll(pageable)).thenReturn(allBooks);
    List<BookDTO> actualBook = bookService.sortBooks(sortBy, MOCK_PAGE, MOCK_SIZE);
    assertThat(actualBook, hasSize(1));
  }
}
