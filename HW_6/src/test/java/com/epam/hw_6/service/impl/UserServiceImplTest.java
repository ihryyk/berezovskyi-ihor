package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.controller.mapper.UserMapper;
import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.hw_6.util.UserTestDataUtil.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks private UserServiceImpl userService;

  @Mock private UserRepository userRepository;

  @Test
  void update() {
    User expectedUser = getUser();
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(getUser());
    when(userRepository.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));
    when(userRepository.save(any())).thenReturn(expectedUser);
    UserDTO savedUser = userService.update(userDTO);
    assertThat(
        savedUser,
        allOf(
            hasProperty("emailAddress", equalTo(expectedUser.getEmailAddress())),
            hasProperty("password", equalTo(expectedUser.getPassword()))));
  }

  @Test
  void updateWithRepositoryException() {
    User user = getUser();
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(user);
    when(userRepository.findById(MOCK_USER_ID)).thenReturn(Optional.empty());
    assertThrows(ServiceException.class, () -> userService.update(userDTO));
  }

  @Test
  void getByEmailAndPassword() {
    User expectedUser = getUser();
    when(userRepository.findUserByEmailAddressAndPassword(MOCK_EMAIL, MOCK_PASSWORD))
        .thenReturn(Optional.of(expectedUser));
    UserDTO actualUser = userService.getByEmailAndPassword(MOCK_EMAIL, MOCK_PASSWORD);
    assertEquals(expectedUser.getEmailAddress(), actualUser.getEmailAddress());
  }

  @Test
  void getByEmailAndPasswordWithRepositoryException() {
    when(userRepository.findUserByEmailAddressAndPassword(MOCK_EMAIL, MOCK_PASSWORD))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceException.class, () -> userService.getByEmailAndPassword(MOCK_EMAIL, MOCK_PASSWORD));
  }

  @Test
  void getByEmail() {
    User expectedUser = getUser();
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.of(expectedUser));
    UserDTO actualUser = userService.getByEmail(MOCK_EMAIL);
    assertEquals(expectedUser.getEmailAddress(), actualUser.getEmailAddress());
  }

  @Test
  void getByEmailWithRepositoryException() {
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.empty());
    assertThrows(ServiceException.class, () -> userService.getByEmail(MOCK_EMAIL));
  }

  @Test
  void getAll() {
    User user = getUser();
    when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
    List<UserDTO> actualUsers = userService.getAll();
    assertThat(actualUsers, hasSize(1));
  }

  @Test
  void changeLockStatus() {
    User user = getUser();
    when(userRepository.findById(MOCK_USER_ID)).thenReturn(Optional.of(user));
    when(userRepository.save(any())).thenReturn(user);
    userService.changeLockStatus(MOCK_USER_ID, LockStatus.BLOCKED);
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void changeLockStatusWithRepositoryException() {
    doThrow(RepositoryException.class).when(userRepository).findById(MOCK_USER_ID);
    assertThrows(
        ServiceException.class,
        () -> userService.changeLockStatus(MOCK_USER_ID, LockStatus.UNLOCKED));
  }

  @Test
  void delete() {
    User user = getUser();
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.of(user));
    doNothing().when(userRepository).delete(user);
    userService.delete(MOCK_EMAIL);
    verify(userRepository, times(1)).delete(user);
  }

  @Test
  void deleteWithRepositoryException() {
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.empty());
    assertThrows(ServiceException.class, () -> userService.delete(MOCK_EMAIL));
  }

  @Test
  void create() {
    User expectedUser = getUser();
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(getUser());
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.empty());
    when(userRepository.save(any())).thenReturn(expectedUser);
    UserDTO savedUser = userService.create(userDTO);
    assertThat(
        savedUser,
        allOf(
            hasProperty("emailAddress", equalTo(expectedUser.getEmailAddress())),
            hasProperty("password", equalTo(expectedUser.getPassword()))));
  }

  @Test
  void createWithRepositoryException() {
    User user = getUser();
    UserDTO userDTO = UserMapper.INSTANCE.mapToDto(user);
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.of(user));
    assertThrows(ServiceException.class, () -> userService.create(userDTO));
  }
}
