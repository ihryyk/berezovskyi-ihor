package com.sprinng.hw_3.service;


import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.ServiceException;

import java.util.List;

public interface UserService {
    public void add(UserDTO userDTO) throws ServiceException;
    public void update(UserDTO userDTO) throws ServiceException;
    public UserDTO getByEmailAndPassword(String email, String password) throws ServiceException;
    public UserDTO getByEmail(String email) throws ServiceException;
    public List<UserDTO> getAll() throws ServiceException;
    public void changeLockStatus(long userId, boolean lockStatus) throws ServiceException;
    public void delete(String email) throws ServiceException;
}
