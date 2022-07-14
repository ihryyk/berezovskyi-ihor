package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.UserDTO;
import com.epam.hw_5.controller.mapper.UserMapper;
import com.epam.hw_5.model.entity.User;
import com.epam.hw_5.repository.JdbcLibrarianRepository;
import com.epam.hw_5.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LibrarianServiceImpl implements LibrarianService {

  private final JdbcLibrarianRepository librarianRepository;

  @Override
  public void create(UserDTO librarianDTO) {
    User librarian = UserMapper.INSTANCE.mapToEntity(librarianDTO);
    librarianRepository.save(librarian);
    log.info("create librarian with email {}", librarian.getEmailAddress());
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDTO> getAll() {
    log.info("get all librarians");
    return librarianRepository.getAll().stream().map(UserMapper.INSTANCE::mapToDto).toList();
  }
}
