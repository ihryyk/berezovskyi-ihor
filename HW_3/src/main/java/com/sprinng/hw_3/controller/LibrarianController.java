package com.sprinng.hw_3.controller;


import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.service.LibrarianService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@Slf4j
public class LibrarianController {
    private final LibrarianService librarianService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/librarian")
    public void createLibrarian(@RequestBody UserDTO librarian) throws ServiceException {
        log.info("create librarian with email {}", librarian.getEmailAddress());
        librarianService.create(librarian);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/librarian")
    public List<UserDTO> getAllLibrarians() throws ServiceException {
        log.info("get all librarians");
        return librarianService.getAll();
    }
}
