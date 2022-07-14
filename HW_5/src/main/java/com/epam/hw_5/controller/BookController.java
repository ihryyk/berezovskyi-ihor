package com.epam.hw_5.controller;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.service.BookService;
import com.epam.hw_5.service.exeption.ControllerException;
import com.epam.hw_5.service.exeption.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "API description for book controller")
@RequestMapping("/book")
public class BookController {
  private final BookService bookService;

  @ApiOperation("Get book by id")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}")
  public BookDTO getBookById(@PathVariable long id) {
    try {
      log.info("get book by id {}", id);
      return bookService.getById(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Get books by author")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "author")
  public List<BookDTO> getBooksByAuthor(
      @RequestParam(value = "author") String author,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    try {
      log.info("get books by author {}", author);
      return bookService.getByAuthor(author, page, size);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Get books by title")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "title")
  public List<BookDTO> getBooksByTitle(
      @RequestParam(value = "title") String title,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    try {
      log.info("get books by title {}", title);
      return bookService.getByTitle(title, page, size);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Get all books")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<BookDTO> getAllBooks(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
    try {
      log.info("get all books");
      return bookService.getAll(page, size);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Update book")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updateBook(@RequestBody @Valid BookDTO bookDTO) {
    try {
      log.info("update book with title {}", bookDTO.getTitle());
      bookService.save(bookDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Delete book")
  @DeleteMapping(value = "{id}")
  public void deleteBookById(@PathVariable Long id) {
    try {
      log.info("delete book by id {}", id);
      bookService.delete(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Create new book")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createBook(@RequestBody @Valid BookDTO bookDTO) {
    try {
      log.info("create new book with title {}", bookDTO.getTitle());
      bookService.save(bookDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Change number of book")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changeNumberOfBook(@PathVariable long id, @RequestBody int number) {
    try {
      log.info("change the number of books to {}", number);
      bookService.changeNumber(id, number);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ApiOperation("Sort books")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "sortedBy")
  public List<BookDTO> getSortedBooks(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(name = "sortedBy") String sortedBy) {
    try {
      log.info("get all books");
      return bookService.sortBooks(sortedBy, size, page);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
