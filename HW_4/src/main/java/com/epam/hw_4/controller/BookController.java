package com.epam.hw_4.controller;


import com.epam.hw_4.controller.dto.BookDTO;
import com.epam.hw_4.service.BookService;
import com.epam.hw_4.service.exeption.ControllerException;
import com.epam.hw_4.service.exeption.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequiredArgsConstructor
@Slf4j
@Api(tags = "API description for book controller")
public class BookController {
    private final BookService bookService;

    @ApiOperation("Get book by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/book/{id}")
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
    @GetMapping(value = "/book/{author}")
    public List<BookDTO> getBooksByAuthor(@PathVariable String author) {
        try {
            log.info("get books by author {}", author);
            return bookService.getByAuthor(author);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }

    @ApiOperation("Get books by title")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/book/{title}")
    public List<BookDTO> getBooksByTitle(@PathVariable String title) {
        try {
            log.info("get books by title {}", title);
            return bookService.getByAuthor(title);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ApiOperation("Get all books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/book")
    public List<BookDTO> getAllBooks() throws ServiceException {
        try {
            log.info("get all books");
            return bookService.getAll();
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ApiOperation("Update book")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/book")
    public void updateBook(@RequestBody @Valid BookDTO bookDTO) {
        try {
            log.info("update book with title {}", bookDTO.getTitle());
            bookService.update(bookDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ApiOperation("Delete book")
    @DeleteMapping(value = "/book/{id}")
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
    @PostMapping(value = "/book")
    public void createBook(@RequestBody @Valid BookDTO bookDTO) {
        try {
            log.info("create new book with title {}", bookDTO.getTitle());
            bookService.add(bookDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ApiOperation("Change number of book")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/book/{id}")
    public void changeNumberOfBook(@PathVariable long id, @RequestBody int number) {
        try {
            log.info("change the number of books to {}", number);
            bookService.changeNumber(id, number);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }
}
