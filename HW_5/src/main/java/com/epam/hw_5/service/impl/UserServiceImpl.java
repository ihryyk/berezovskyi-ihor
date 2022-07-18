package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.UserDTO;
import com.epam.hw_5.controller.mapper.UserMapper;
import com.epam.hw_5.model.entity.User;
import com.epam.hw_5.model.enums.LockStatus;
import com.epam.hw_5.repository.UserRepository;
import com.epam.hw_5.service.UserService;
import com.epam.hw_5.service.exeption.RepositoryException;
import com.epam.hw_5.service.exeption.ServiceException;
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
  public void save(UserDTO userDTO) {
    log.info("save user with email {}", userDTO.getEmailAddress());
    User user = UserMapper.INSTANCE.mapToEntity(userDTO);
    userRepository.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO getByEmailAndPassword(String email, String password) throws RepositoryException {
    Optional<User> user = userRepository.findUserByEmailAddressAndPassword(email, password);
    if (user.isPresent()) {
      throw new RepositoryException(
          format("User with email %s and password %s not found", email, password));
    }
    log.info("get user by email{} and password {}", email, password);
    return UserMapper.INSTANCE.mapToDto(user.get());
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO getByEmail(String email) throws RepositoryException {
    try {
      Optional<User> user = userRepository.findUserByEmailAddress(email);
      log.info("get user by email{}", email);
      if (user.isPresent()) {
        throw new RepositoryException(format("User with email %s not found", email));
      }
      return UserMapper.INSTANCE.mapToDto(user.get());
    } catch (RepositoryException exception) {
      throw new ServiceException();
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
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("User with id " + id + " not found"));
    user.setLockStatus(lockStatus);
    userRepository.save(user);
    log.info("change lock status to {} in user with id {}", lockStatus, id);
  }

  @Override
  public void delete(String email) {
    log.info("delete User with email {}", email);
    userRepository.deleteByEmailAddress(email);
  }
}
