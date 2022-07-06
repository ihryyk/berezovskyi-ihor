package com.epam.hw_4.service;


import com.epam.hw_4.controller.dto.UserDTO;
import com.epam.hw_4.service.exeption.ServiceException;

import java.util.List;

public interface LibrarianService {
    public void create(UserDTO librarian) throws ServiceException;

    public List<UserDTO> getAll() throws ServiceException;
}
