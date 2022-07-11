package com.sprinng.hw_3.controller;

import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.service.BookService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@Slf4j
public class BookController {
  private final BookService bookService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/book/{id}")
  public BookDTO getBookById(@PathVariable long id) throws ServiceException {
    log.info("get book by id {}", id);
    return bookService.getById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/books/{author}")
  public List<BookDTO> getBooksByAuthor(@PathVariable String author) throws ServiceException {
    log.info("get books by author {}", author);
    return bookService.getByAuthor(author);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/book/{title}")
  public List<BookDTO> getBooksByTitle(@PathVariable String title) throws ServiceException {
    log.info("get books by title {}", title);
    return bookService.getByAuthor(title);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/book")
  public List<BookDTO> getAllBooks() throws ServiceException {
    log.info("get all books");
    return bookService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/book")
  public void updateBook(@RequestBody BookDTO bookDTO) throws ServiceException {
    log.info("update book with title {}", bookDTO.getTitle());
    bookService.update(bookDTO);
  }

  @DeleteMapping(value = "/book/{id}")
  public void deleteBookById(@PathVariable Long id) throws ServiceException {
    log.info("delete book by id {}", id);
    bookService.delete(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/book")
  public void createBook(@RequestBody BookDTO bookDTO) throws ServiceException {
    log.info("create new book with title {}", bookDTO.getTitle());
    bookService.add(bookDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/book/{id}")
  public void changeNumberOfBook(@PathVariable long id, @RequestBody int number) {
    log.info("change the number of books to {}", number);
    bookService.changeNumber(id, number);
  }
}
