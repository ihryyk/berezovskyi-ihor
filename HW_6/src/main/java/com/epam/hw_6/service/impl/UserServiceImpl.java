package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.controller.mapper.UserMapper;
import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.UserService;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDTO create(UserDTO userDTO) {
    log.info("create user with email {}", userDTO.getEmailAddress());
    User user = UserMapper.INSTANCE.mapToEntity(userDTO);
    Optional<User> existedUser = userRepository.findUserByEmailAddress(user.getEmailAddress());
    if (existedUser.isPresent()) {
      throw new ServiceException(
          format("User with email %s is already exists", user.getEmailAddress()));
    }
    return UserMapper.INSTANCE.mapToDto(userRepository.save(user));
  }

  @Override
  public UserDTO update(UserDTO userDTO) {
    log.info("update user with email {}", userDTO.getEmailAddress());
    User user = null;
    try {
      user =
          userRepository
              .findById(userDTO.id)
              .orElseThrow(
                  () -> new RepositoryException("User with id " + userDTO.id + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    return UserMapper.INSTANCE.mapToDto(userRepository.save(user));
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO getByEmailAndPassword(String email, String password) throws RepositoryException {
    Optional<User> user = userRepository.findUserByEmailAddressAndPassword(email, password);
    if (user.isPresent()) {
      log.info("get user by email{} and password {}", email, password);
      return UserMapper.INSTANCE.mapToDto(user.get());
    } else {
      log.error("User with email{} and password {} don't exist", email, password);
      throw new ServiceException(
          format("User with email %s and password %s not found", email, password));
    }
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO getByEmail(String email) throws RepositoryException {
    try {
      Optional<User> user = userRepository.findUserByEmailAddress(email);
      log.info("get user by email{}", email);
      if (user.isPresent()) {
        return UserMapper.INSTANCE.mapToDto(user.get());
      } else {
        throw new RepositoryException(format("User with email %s not found", email));
      }
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDTO> getAll() {
    log.info("get all users");
    return userRepository.findAll().stream().map(UserMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional
  public void changeLockStatus(long id, LockStatus lockStatus) throws RepositoryException {
    User user = null;
    try {
      user =
          userRepository
              .findById(id)
              .orElseThrow(() -> new RepositoryException("User with id " + id + " not found"));
      user.setLockStatus(lockStatus);
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    userRepository.save(user);
    log.info("change lock status to {} in user with id {}", lockStatus, id);
  }

  @Override
  @Transactional
  public void delete(String email) {
    User deletedUser = null;
    try {
      deletedUser =
          userRepository
              .findUserByEmailAddress(email)
              .orElseThrow(
                  () -> new RepositoryException("User with email " + email + " not found"));
    } catch (RepositoryException exception) {
      throw new ServiceException(exception.getMessage());
    }
    log.info("delete User with email {}", email);
    userRepository.delete(deletedUser);
  }
}
