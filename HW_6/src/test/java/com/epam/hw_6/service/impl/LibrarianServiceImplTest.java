package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.controller.mapper.UserMapper;
import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.repository.LibrarianRepository;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.exeption.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.hw_6.util.UserTestDataUtil.MOCK_EMAIL;
import static com.epam.hw_6.util.UserTestDataUtil.getLibrarian;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {

  @InjectMocks private LibrarianServiceImpl librarianService;

  @Mock private LibrarianRepository librarianRepository;

  @Mock private UserRepository userRepository;

  @Test
  void create() {
    User expectedLibrarian = getLibrarian();
    UserDTO librarianDTO = UserMapper.INSTANCE.mapToDto(getLibrarian());
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL)).thenReturn(Optional.empty());
    when(librarianRepository.save(any())).thenReturn(expectedLibrarian);
    UserDTO savedLibrarian = librarianService.create(librarianDTO);
    assertThat(
        savedLibrarian,
        allOf(
            hasProperty("emailAddress", equalTo(expectedLibrarian.getEmailAddress())),
            hasProperty("password", equalTo(expectedLibrarian.getPassword()))));
  }

  @Test
  void createWithRepositoryException() {
    User expectedLibrarian = getLibrarian();
    UserDTO librarianDTO = UserMapper.INSTANCE.mapToDto(getLibrarian());
    when(userRepository.findUserByEmailAddress(MOCK_EMAIL))
        .thenReturn(Optional.of(expectedLibrarian));
    assertThrows(ServiceException.class, () -> librarianService.create(librarianDTO));
  }

  @Test
  void getAll() {
    User librarian = getLibrarian();
    when(librarianRepository.findAll()).thenReturn(Collections.singletonList(librarian));
    List<UserDTO> actualUsers = librarianService.getAll();
    assertThat(actualUsers, hasSize(1));
  }
}
