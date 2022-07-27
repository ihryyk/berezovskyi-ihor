package com.epam.hw_6.controller;

import com.epam.hw_6.config.TestWebConfig;
import com.epam.hw_6.controller.dto.BookDTO;
import com.epam.hw_6.controller.mapper.BookMapper;
import com.epam.hw_6.service.BookService;
import com.epam.hw_6.service.exeption.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.epam.hw_6.util.BookTestDataUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = BookController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BookService bookService;

  @Test
  void getBookById() throws Exception {
    BookDTO bookDto = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookService.getById(MOCK_BOOK_ID)).thenReturn(bookDto);
    mockMvc
        .perform(get("/book/" + MOCK_BOOK_ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value(bookDto.title));
  }

  @Test
  void getBookByIdWithServiceException() throws Exception {
    when(bookService.getById(MOCK_BOOK_ID)).thenThrow(new ServiceException());
    mockMvc.perform(get("/book/" + MOCK_BOOK_ID)).andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  void getBooksByAuthor() throws Exception {
    BookDTO bookDto = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookService.getByAuthor(MOCK_AUTHOR, MOCK_PAGE, MOCK_SIZE))
        .thenReturn(Collections.singletonList(bookDto));
    mockMvc
        .perform(get("/book?author=author&page=1&size=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].author").value(bookDto.author));
  }

  @Test
  void getBooksByTitle() throws Exception {
    BookDTO bookDto = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookService.getByTitle(MOCK_TITLE, MOCK_PAGE, MOCK_SIZE))
        .thenReturn(Collections.singletonList(bookDto));
    mockMvc
        .perform(get("/book?title=title&page=1&size=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].author").value(bookDto.author));
  }

  @Test
  void getAllBooks() throws Exception {
    BookDTO bookDto = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookService.getAll(MOCK_PAGE, MOCK_SIZE)).thenReturn(Collections.singletonList(bookDto));
    mockMvc
        .perform(get("/book?page=1&size=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].author").value(bookDto.author));
  }

  @Test
  void updateBook() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    when(bookService.save(any())).thenReturn(bookDTO);
    mockMvc
        .perform(
            put("/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(bookService).save(bookDTO);
  }

  @Test
  void updateBookReturnsBadRequestForInvalidBookNumber() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    bookDTO.setNumber(-1);
    when(bookService.save(any())).thenReturn(bookDTO);
    mockMvc
        .perform(
            put("/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateBookWithServiceException() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    doThrow(ServiceException.class).when(bookService).save(bookDTO);
    mockMvc
        .perform(
            put("/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteBookById() throws Exception {
    doNothing().when(bookService).delete(MOCK_BOOK_ID);
    mockMvc.perform(delete("/book/" + MOCK_BOOK_ID)).andDo(print()).andExpect(status().isOk());
    verify(bookService).delete(MOCK_BOOK_ID);
  }

  @Test
  void deleteBookByIdWithException() throws Exception {
    doThrow(ServiceException.class).when(bookService).delete(MOCK_BOOK_ID);
    mockMvc
        .perform(delete("/book/" + MOCK_BOOK_ID))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createBook() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    doThrow(ServiceException.class).when(bookService).save(bookDTO);
    mockMvc
        .perform(
            post("/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createBookReturnsBadRequestForInvalidTitle() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    bookDTO.setTitle(" ");
    when(bookService.save(any())).thenReturn(bookDTO);
    mockMvc
        .perform(
            put("/book")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void changeNumberOfBook() throws Exception {
    int newNumber = 10;
    doNothing().when(bookService).changeNumber(MOCK_BOOK_ID, newNumber);
    mockMvc
        .perform(
            patch("/book/" + MOCK_BOOK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newNumber)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(bookService).changeNumber(MOCK_BOOK_ID, newNumber);
  }

  @Test
  void changeNumberOfBookWithServiceException() throws Exception {
    int newNumber = 10;
    doThrow(ServiceException.class).when(bookService).changeNumber(MOCK_BOOK_ID, newNumber);
    mockMvc
        .perform(
            patch("/book/" + MOCK_BOOK_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newNumber)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getSortedBooks() throws Exception {
    BookDTO bookDto = BookMapper.INSTANCE.mapToDto(getBook());
    doReturn(Collections.singletonList(bookDto))
        .when(bookService)
        .sortBooks(MOCK_SORTED_BY, MOCK_SIZE, MOCK_PAGE);
    mockMvc
        .perform(get("/book?sortedBy=title&page=1&size=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].author").value(bookDto.author));
  }
}
