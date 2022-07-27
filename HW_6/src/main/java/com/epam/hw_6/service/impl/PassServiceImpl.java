package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.BookDTO;
import com.epam.hw_6.controller.dto.PassDTO;
import com.epam.hw_6.controller.mapper.BookMapper;
import com.epam.hw_6.controller.mapper.PassMapper;
import com.epam.hw_6.model.entity.Pass;
import com.epam.hw_6.model.enums.PassStatus;
import com.epam.hw_6.repository.PassRepository;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.PassService;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

  private final PassRepository passRepository;

  private final UserRepository userRepository;

  @Override
  @Transactional
  public void changePassStatus(long id, PassStatus passStatus) throws RepositoryException {
    log.info("change pass status to {} in pass with id {}", passStatus, id);
    Pass pass = null;
    try {
      pass =
          passRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    passRepository.save(pass);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> getBooksByPassId(long id) throws ServiceException {
    log.info("get books from pass by pass id {}", id);
    Pass pass = null;
    try {
      pass =
          passRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    return pass.getOrder().getOrderBooks().stream().map(BookMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<PassDTO> getAll() {
    log.info("get all passes");
    return passRepository.findAll().stream().map(PassMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<PassDTO> getAllByUserId(long userId) {
    log.info("get all passes by user id {}", userId);
    try {
      userRepository
          .findById(userId)
          .orElseThrow(() -> new RepositoryException("User with id " + userId + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    return passRepository.getAllByUserId(userId).stream()
        .map(PassMapper.INSTANCE::mapToDto)
        .toList();
  }

  @Override
  public PassDTO save(PassDTO passDTO) {
    log.info("save pass");
    Pass pass = PassMapper.INSTANCE.mapToEntity(passDTO);
    return PassMapper.INSTANCE.mapToDto(passRepository.save(pass));
  }

  @Override
  @Transactional
  public void updatePenalty(long id, int penalty) throws RepositoryException {
    Pass pass = null;
    try {
      pass =
          passRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    pass.setPenalty(penalty);
    log.info("update penalty to {} in pass with id {}", penalty, id);
    passRepository.save(pass);
  }
}
