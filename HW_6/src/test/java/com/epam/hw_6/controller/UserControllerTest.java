package com.epam.hw_6.controller;

import com.epam.hw_6.config.TestWebConfig;
import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.controller.mapper.UserMapper;
import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.service.UserService;
import com.epam.hw_6.service.exeption.ServiceException;
import com.epam.hw_6.util.UserTestDataUtil;
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

import static com.epam.hw_6.util.UserTestDataUtil.MOCK_EMAIL;
import static com.epam.hw_6.util.UserTestDataUtil.MOCK_USER_ID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class UserControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private UserService userService;

  @Test
  void getUser() throws Exception {
    UserDTO userDto = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    when(userService.getByEmail(MOCK_EMAIL)).thenReturn(userDto);
    mockMvc
        .perform(get("/user/" + MOCK_EMAIL))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.emailAddress").value(userDto.getEmailAddress()));
  }

  @Test
  void getUserWithServiceException() throws Exception {
    when(userService.getByEmail(MOCK_EMAIL)).thenThrow(new ServiceException());
    mockMvc.perform(get("/user/" + MOCK_EMAIL)).andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  void getAllUsers() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    when(userService.getAll()).thenReturn(Collections.singletonList(userDTO));
    mockMvc
        .perform(get("/user"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value(userDTO.getName()));
  }

  @Test
  void createUser() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    when(userService.create(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(userService).create(userDTO);
  }

  @Test
  void createUserReturnsBadRequestForInvalidUserName() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    userDTO.setName("i");
    when(userService.create(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createUserReturnsBadRequestForInvalidPassword() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    userDTO.setPassword("ihorBerezovskyi200");
    when(userService.create(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void createUserWithServiceException() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    doThrow(ServiceException.class).when(userService).create(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateUser() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    when(userService.update(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            put("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(userService).update(userDTO);
  }

  @Test
  void updateUserReturnsBadRequestForInvalidUserName() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    userDTO.setPassword("ihorBerezovskyi200");
    when(userService.update(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateUserReturnsBadRequestForInvalidUserPassword() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    userDTO.setPassword("i");
    when(userService.update(any())).thenReturn(userDTO);
    mockMvc
        .perform(
            post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateUserWithServiceException() throws Exception {
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(UserTestDataUtil.getUser());
    doThrow(ServiceException.class).when(userService).update(userDTO);
    mockMvc
        .perform(
            put("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteUser() throws Exception {
    doNothing().when(userService).delete(MOCK_EMAIL);
    mockMvc.perform(delete("/user/" + MOCK_EMAIL)).andDo(print()).andExpect(status().isOk());
    verify(userService).delete(MOCK_EMAIL);
  }

  @Test
  void deleteUserWithServiceException() throws Exception {
    doThrow(ServiceException.class).when(userService).delete(MOCK_EMAIL);
    mockMvc
        .perform(delete("/user/" + MOCK_EMAIL))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void changeLockStatus() throws Exception {
    doNothing().when(userService).changeLockStatus(MOCK_USER_ID, LockStatus.BLOCKED);
    mockMvc
        .perform(
            patch("/user/" + MOCK_USER_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(LockStatus.BLOCKED)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(userService).changeLockStatus(1, LockStatus.BLOCKED);
  }

  @Test
  void changeLockStatusWithServiceException() throws Exception {
    doThrow(ServiceException.class).when(userService).changeLockStatus(MOCK_USER_ID, LockStatus.BLOCKED);
    mockMvc
        .perform(
            patch("/user/" + MOCK_USER_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(LockStatus.BLOCKED)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
