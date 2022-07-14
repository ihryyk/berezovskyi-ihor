package com.epam.hw45.controller;

import com.epam.hw_4.controller.dto.UserDTO;
import com.epam.hw_4.service.LibrarianService;
import com.epam.hw_4.service.exeption.ControllerException;
import com.epam.hw_4.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/librarian")
public class LibrarianController {
  private final LibrarianService librarianService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createLibrarian(@RequestBody UserDTO librarian) {
    try {
      log.info("create librarian with email {}", librarian.getEmailAddress());
      librarianService.create(librarian);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<UserDTO> getAllLibrarians() {
    try {
      log.info("get all librarians");
      return librarianService.getAll();
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
