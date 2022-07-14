package com.epam.hw_5.service;

import com.epam.hw_5.controller.dto.UserDTO;
import com.epam.hw_5.model.enums.LockStatus;
import com.epam.hw_5.service.exeption.ServiceException;

import java.util.List;

public interface UserService {

  public void save(UserDTO userDTO) throws ServiceException;

  public UserDTO getByEmailAndPassword(String email, String password) throws ServiceException;

  public UserDTO getByEmail(String email) throws ServiceException;

  public List<UserDTO> getAll() throws ServiceException;

  public void changeLockStatus(long userId, LockStatus lockStatus) throws ServiceException;

  public void delete(String email) throws ServiceException;
}
