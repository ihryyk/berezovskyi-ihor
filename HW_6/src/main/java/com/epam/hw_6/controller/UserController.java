package com.epam.hw_6.controller;

import com.epam.hw_6.controller.dto.UserDTO;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.service.UserService;
import com.epam.hw_6.service.exeption.ControllerException;
import com.epam.hw_6.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{email}")
  public UserDTO getUser(@PathVariable String email) {
    log.info("get user by email{}", email);
    return userService.getByEmail(email);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<UserDTO> getAllUsers() throws ControllerException {
    log.info("get all users");
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createUser(@Valid @RequestBody UserDTO userDTO) {
    try {
      log.info("add new user with email {}", userDTO.getEmailAddress());
      userService.create(userDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updateUser(@Valid @RequestBody UserDTO userDTO) throws ControllerException {
    try {
      log.info("update user with email {}", userDTO.getEmailAddress());
      userService.update(userDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @DeleteMapping(value = "{email}")
  public void deleteUser(@PathVariable String email) throws ControllerException {
    try {
      log.info("delete User with email {}", email);
      userService.delete(email);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changeLockStatus(@PathVariable long id, @RequestBody LockStatus lockStatus)
      throws ControllerException {
    try {
      log.info("change lock status to {} in user with id {}", lockStatus, id);
      userService.changeLockStatus(id, lockStatus);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
