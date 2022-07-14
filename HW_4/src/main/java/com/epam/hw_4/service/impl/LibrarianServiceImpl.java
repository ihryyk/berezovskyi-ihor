package com.epam.hw_4.service.impl;

import com.epam.hw_4.controller.dto.UserDTO;
import com.epam.hw_4.controller.mapper.UserMapper;
import com.epam.hw_4.model.entity.User;
import com.epam.hw_4.service.LibrarianService;
import com.epam.hw_4.service.exeption.ServiceException;
import com.epam.hw_4.repository.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

  private final LibrarianRepository librarianRepository;

  @Override
  public void create(UserDTO librarianDTO) throws ServiceException {
    User librarian = UserMapper.INSTANCE.mapToEntity(librarianDTO);
    librarianRepository.create(librarian);
    log.info("create librarian with email {}", librarian.getEmailAddress());
  }

  @Override
  public List<UserDTO> getAll() throws ServiceException {
    log.info("get all librarians");
    return librarianRepository.getAll().stream().map(UserMapper.INSTANCE::mapToDto).toList();
  }
}
