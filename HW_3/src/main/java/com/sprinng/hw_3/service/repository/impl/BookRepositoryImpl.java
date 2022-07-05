package com.sprinng.hw_3.service.repository.impl;


import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {


    @Override
    public Book getById(long bookId) throws RepositoryExeption {
        return null;
    }

    @Override
    public List<Book> getByAuthor(String author) throws RepositoryExeption {
        return null;
    }

    @Override
    public List<Book> getAll() throws RepositoryExeption {
        return null;
    }

    @Override
    public List<Book> getByTitle(String title) throws RepositoryExeption {
        return null;
    }

    @Override
    public void changeNumber(long bookId, int newNumber) throws RepositoryExeption {

    }

    @Override
    public void update(Book updatedBook) throws RepositoryExeption {

    }

    @Override
    public void delete(long id) throws RepositoryExeption {

    }

    @Override
    public void add(Book book) throws RepositoryExeption {

    }
}
