package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.UserDTO;
import com.epam.hw_5.controller.mapper.UserMapper;
import com.epam.hw_5.model.entity.User;
import com.epam.hw_5.repository.LibrarianRepository;
import com.epam.hw_5.repository.UserRepository;
import com.epam.hw_5.service.LibrarianService;
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
public class LibrarianServiceImpl implements LibrarianService {

  private final LibrarianRepository librarianRepository;
  private final UserRepository userRepository;

  @Override
  public UserDTO create(UserDTO librarianDTO) {
    User librarian = UserMapper.INSTANCE.mapToEntity(librarianDTO);
    log.info("create librarian with email {}", librarian.getEmailAddress());
    Optional<User> existedUser = userRepository.findUserByEmailAddress(librarian.getEmailAddress());
    if (existedUser.isPresent()) {
      throw new ServiceException(
          format("librarian with email %s is already exists", librarian.getEmailAddress()));
    }
    return UserMapper.INSTANCE.mapToDto(librarianRepository.save(librarian));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserDTO> getAll() {
    log.info("get all librarians");
    return librarianRepository.findAll().stream().map(UserMapper.INSTANCE::mapToDto).toList();
  }
}
