package com.epam.hw_6.service;

import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.service.exeption.ServiceException;

import java.util.List;

public interface LibrarianService {
  public UserDTO create(UserDTO librarian) throws ServiceException;

  public List<UserDTO> getAll() throws ServiceException;
}
