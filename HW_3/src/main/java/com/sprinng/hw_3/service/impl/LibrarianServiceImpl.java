package com.sprinng.hw_3.service.impl;




import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.controller.mapper.UserMapper;
import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.LibrarianService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import com.sprinng.hw_3.service.repository.LibrarianRepository;
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
