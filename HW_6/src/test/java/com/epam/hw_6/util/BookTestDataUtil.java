package com.epam.hw_6.util;

import com.epam.hw_6.model.entity.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookTestDataUtil {
  public static final String MOCK_AUTHOR = "author";
  public static final String MOCK_TITLE = "title";
  public static final String MOCK_PUBLISHING_HOUSE = "Publishing house";
  public static final int MOCK_NUMBER = 1;
  public static final int MOCK_YEAR = 2001;
  public static final int MOCK_PAGE = 1;
  public static final int MOCK_SIZE = 1;
  public static final long MOCK_BOOK_ID = 1;
  public static final String MOCK_SORTED_BY = "title";

  public static Book getBook() {
    return Book.builder()
        .id(MOCK_BOOK_ID)
        .author(MOCK_AUTHOR)
        .title(MOCK_TITLE)
        .year(MOCK_YEAR)
        .publishingHouse(MOCK_PUBLISHING_HOUSE)
        .number(MOCK_NUMBER)
        .deleted(false)
        .build();
  }

  public static Pageable getPageable() {
    return PageRequest.of(MOCK_SIZE, MOCK_PAGE);
  }
}
