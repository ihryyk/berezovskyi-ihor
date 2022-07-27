package com.epam.hw_6.controller;

import com.epam.hw_6.config.TestWebConfig;
import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.controller.mapper.UserMapper;
import com.epam.hw_6.service.LibrarianService;
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

import static com.epam.hw_6.util.UserTestDataUtil.getUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = LibrarianController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class LibrarianControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private LibrarianService librarianService;

  @Test
  void createLibrarian() throws Exception {
    UserDTO librarianDTO = UserMapper.INSTANCE.mapToDto(getUser());
    when(librarianService.create(any())).thenReturn(librarianDTO);
    mockMvc
        .perform(
            post("/librarian")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(librarianDTO)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(librarianService).create(librarianDTO);
  }

  @Test
  void createLibrarianWithServiceException() throws Exception {
    UserDTO librarianDTO = UserMapper.INSTANCE.mapToDto(getUser());
    doThrow(ServiceException.class).when(librarianService).create(librarianDTO);
    mockMvc
        .perform(
            post("/librarian")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(librarianDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllLibrarians() throws Exception {
    UserDTO librarianDTO = UserMapper.INSTANCE.mapToDto(getUser());
    when(librarianService.getAll()).thenReturn(Collections.singletonList(librarianDTO));
    mockMvc
        .perform(get("/librarian"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].emailAddress").value(librarianDTO.getEmailAddress()));
  }
}
