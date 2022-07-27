package com.epam.hw_6.controller;

import com.epam.hw_6.config.TestWebConfig;
import com.epam.hw_6.controller.dto.BookDTO;
import com.epam.hw_6.controller.dto.PassDTO;
import com.epam.hw_6.controller.mapper.BookMapper;
import com.epam.hw_6.controller.mapper.PassMapper;
import com.epam.hw_6.model.enums.PassStatus;
import com.epam.hw_6.service.PassService;
import com.epam.hw_6.service.exeption.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static com.epam.hw_6.util.BookTestDataUtil.getBook;
import static com.epam.hw_6.util.PassTestDataUtil.MOCK_PASS_ID;
import static com.epam.hw_6.util.PassTestDataUtil.getPass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PassController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class PassControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private PassService passService;

  @Test
  void getAllPasses() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    when(passService.getAll()).thenReturn(Collections.singletonList(passDTO));
    mockMvc
        .perform(get("/pass"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].penalty").value(passDTO.penalty));
  }

  @Test
  void getPassesByUserId() throws Exception {
    long userId = 1;
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    when(passService.getAllByUserId(userId)).thenReturn(Collections.singletonList(passDTO));
    mockMvc
        .perform(get("/pass?userId=" + userId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].penalty").value(passDTO.penalty));
  }

  @Test
  void getPassesByUserIdWithServiceException() throws Exception {
    long userId = 1;
    doThrow(ServiceException.class).when(passService).getAllByUserId(userId);
    mockMvc
        .perform(get("/pass?userId=" + userId))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getBooksFromPassByPassId() throws Exception {
    BookDTO bookDTO = BookMapper.INSTANCE.mapToDto(getBook());
    when(passService.getBooksByPassId(MOCK_PASS_ID)).thenReturn(Collections.singletonList(bookDTO));
    mockMvc
        .perform(get("/pass/" + MOCK_PASS_ID + "/books"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value(bookDTO.title));
  }

  @Test
  void getBooksFromPassByPassIdWithServiceException() throws Exception {
    doThrow(ServiceException.class).when(passService).getBooksByPassId(MOCK_PASS_ID);
    mockMvc
        .perform(get("/pass/" + MOCK_PASS_ID + "/books"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createPass() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    when(passService.save(any())).thenReturn(passDTO);
    mockMvc
        .perform(
            post("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(passService).save(passDTO);
  }

  @Test
  void createPassReturnsBadRequestForInvalidPenalty() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    passDTO.setPenalty(-1);
    when(passService.save(any())).thenReturn(passDTO);
    mockMvc
        .perform(
            post("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createPassWithServiceException() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    doThrow(ServiceException.class).when(passService).save(passDTO);
    mockMvc
        .perform(
            post("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updatePass() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    when(passService.save(any())).thenReturn(passDTO);
    mockMvc
        .perform(
            put("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(passService).save(passDTO);
  }

  @Test
  void updatePassWithServiceException() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    doThrow(ServiceException.class).when(passService).save(passDTO);
    mockMvc
        .perform(
            put("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createPassReturnsBadRequestForInvalidEndDate() throws Exception {
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
    passDTO.setEndDate(dateFormat.parse("2020-01-01"));
    when(passService.save(any())).thenReturn(passDTO);
    mockMvc
        .perform(
            put("/pass")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void changePassStatus() throws Exception {
    doNothing().when(passService).changePassStatus(MOCK_PASS_ID, PassStatus.FINISH);
    mockMvc
        .perform(
            patch("/pass/" + MOCK_PASS_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PassStatus.FINISH)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(passService).changePassStatus(MOCK_PASS_ID, PassStatus.FINISH);
  }

  @Test
  void changePassStatusWithServiceException() throws Exception {
    doThrow(ServiceException.class)
        .when(passService)
        .changePassStatus(MOCK_PASS_ID, PassStatus.FINISH);
    mockMvc
        .perform(
            patch("/pass/" + MOCK_PASS_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(PassStatus.FINISH)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
