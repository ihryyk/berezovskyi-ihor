package com.epam.hw_5.service;

import com.epam.hw_5.controller.dto.UserDTO;
import com.epam.hw_5.service.exeption.ServiceException;

import java.util.List;

public interface LibrarianService {
  public UserDTO create(UserDTO librarian) throws ServiceException;

  public List<UserDTO> getAll() throws ServiceException;
}
