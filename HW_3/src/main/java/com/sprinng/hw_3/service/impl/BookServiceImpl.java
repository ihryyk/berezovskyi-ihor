package com.sprinng.hw_3.service.impl;


import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.controller.mapper.BookMapper;
import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.service.BookService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import com.sprinng.hw_3.service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDTO getById(long id) throws ServiceException {

        Book book = bookRepository.getById(id);

        if (book == null) {
            throw new ServiceException(format("Book with id %o not found", id));
        }
        log.info("get book by id {}", id);
        return BookMapper.INSTANCE.mapToDto(book);
    }

    @Override
    public List<BookDTO> getByAuthor(String author) throws ServiceException {
        log.info("get books by author {}", author);
        return bookRepository.getByAuthor(author).stream().map(BookMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public List<BookDTO> getAll() throws ServiceException {
        log.info("get all books");
        return bookRepository.getAll().stream().map(BookMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public List<BookDTO> getByTitle(String title) throws ServiceException {
        log.info("get books by title {}", title);
        return bookRepository.getByTitle(title).stream().map(BookMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public void changeNumber(long id, int newNumber) throws ServiceException {
        log.info("change the number of books to {}", newNumber);
        bookRepository.changeNumber(id, newNumber);
    }

    @Override
    public void update(BookDTO updatedBookDTO) throws ServiceException {
        log.info("update book with title {}", updatedBookDTO.getTitle());
        Book updatedBook = BookMapper.INSTANCE.mapToEntity(updatedBookDTO);
        bookRepository.update(updatedBook);
    }

    @Override
    public void delete(long id) throws ServiceException {
        bookRepository.delete(id);
        log.info("delete book by id {}", id);
    }

    @Override
    public void add(BookDTO bookDTT) throws ServiceException {
        Book book = BookMapper.INSTANCE.mapToEntity(bookDTT);
        log.info("create new book with title {}", book.getTitle());
        bookRepository.add(book);
    }
}
