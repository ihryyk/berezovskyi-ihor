package com.sprinng.hw_3.service;


import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.ServiceException;

import java.util.List;

public interface LibrarianService {
    public void create(UserDTO librarian) throws ServiceException;
    public List<UserDTO> getAll() throws ServiceException;
}
