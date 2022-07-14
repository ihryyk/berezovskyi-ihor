package com.epam.hw_5.repository;

import com.epam.hw_5.model.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

  public List<Book> findAllByAuthor(String author, Pageable pageable);

  public List<Book> findAllByTitle(String title, Pageable pageable);

  @Modifying
  @Query("update Book set number = ?2, id=?1")
  public void changeNumber(@Param("id") long id, @Param("number") int number);
}
