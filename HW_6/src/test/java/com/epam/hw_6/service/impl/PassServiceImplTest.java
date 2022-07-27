package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.BookDTO;
import com.epam.hw_6.controller.dto.PassDTO;
import com.epam.hw_6.controller.mapper.PassMapper;
import com.epam.hw_6.model.entity.Pass;
import com.epam.hw_6.model.enums.PassStatus;
import com.epam.hw_6.repository.PassRepository;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.hw_6.util.PassTestDataUtil.*;
import static com.epam.hw_6.util.UserTestDataUtil.MOCK_USER_ID;
import static com.epam.hw_6.util.UserTestDataUtil.getUser;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassServiceImplTest {

  @InjectMocks private PassServiceImpl passService;

  @Mock private PassRepository passRepository;

  @Mock private UserRepository userRepository;

  @Test
  void changePassStatus() {
    Pass pass = getPass();
    when(passRepository.findById(MOCK_PASS_ID)).thenReturn(Optional.of(pass));
    when(passRepository.save(any())).thenReturn(pass);
    passService.changePassStatus(MOCK_PASS_ID, PassStatus.ACTIVE);
    verify(passRepository, times(1)).save(pass);
  }

  @Test
  void changePassStatusWithRepositoryException() {
    doThrow(RepositoryException.class).when(passRepository).findById(MOCK_PASS_ID);
    assertThrows(
        ServiceException.class,
        () -> passService.changePassStatus(MOCK_PASS_ID, PassStatus.ACTIVE));
  }

  @Test
  void getBooksById() {
    Pass pass = getPass();
    when(passRepository.findById(MOCK_PASS_ID)).thenReturn(Optional.of(pass));
    List<BookDTO> actualBooks = passService.getBooksByPassId(MOCK_PASS_ID);
    assertThat(actualBooks, hasSize(1));
  }

  @Test
  void getBooksByIdWithRepositoryException() {
    doThrow(RepositoryException.class).when(passRepository).findById(MOCK_PASS_ID);
    assertThrows(ServiceException.class, () -> passService.getBooksByPassId(MOCK_PASS_ID));
  }

  @Test
  void getAll() {
    when(passRepository.findAll()).thenReturn(Collections.singletonList(getPass()));
    List<PassDTO> actualPasses = passService.getAll();
    assertThat(actualPasses, hasSize(1));
  }

  @Test
  void getAllByUserId() {
    when(userRepository.findById(MOCK_USER_ID)).thenReturn(Optional.of(getUser()));
    when(passRepository.getAllByUserId(MOCK_USER_ID))
        .thenReturn(Collections.singletonList(getPass()));
    List<PassDTO> actualPasses = passService.getAllByUserId(MOCK_USER_ID);
    assertThat(actualPasses, hasSize(1));
  }

  @Test
  void getAllByUserIdWithRepositoryException() {
    doThrow(RepositoryException.class).when(userRepository).findById(MOCK_USER_ID);
    assertThrows(ServiceException.class, () -> passService.getAllByUserId(MOCK_USER_ID));
  }

  @Test
  void save() {
    Pass expectedPass = getPass();
    PassDTO passDTO = PassMapper.INSTANCE.mapToDto(getPass());
    when(passRepository.save(any())).thenReturn(expectedPass);
    PassDTO savedPass = passService.save(passDTO);
    assertThat(
        savedPass,
        allOf(
            hasProperty("startDate", CoreMatchers.equalTo(expectedPass.getStartDate())),
            hasProperty("endDate", CoreMatchers.equalTo(expectedPass.getEndDate()))));
  }

  @Test
  void updatePenalty() {
    Pass pass = getPass();
    when(passRepository.findById(MOCK_PASS_ID)).thenReturn(Optional.of(pass));
    when(passRepository.save(any())).thenReturn(pass);
    passService.updatePenalty(MOCK_PASS_ID, MOCK_PENALTY);
    verify(passRepository, times(1)).save(pass);
  }

  @Test
  void updatePenaltyWithRepositoryException() {
    doThrow(RepositoryException.class).when(passRepository).findById(MOCK_PASS_ID);
    assertThrows(
        ServiceException.class, () -> passService.updatePenalty(MOCK_PASS_ID, MOCK_PENALTY));
  }
}
