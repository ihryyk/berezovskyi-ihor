package com.sprinng.hw_3.controller;

import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.service.UserService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/user/{email}")
  public UserDTO getUser(@PathVariable String email) throws ServiceException {
    log.info("get user by email{}", email);
    return userService.getByEmail(email);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/user")
  public List<UserDTO> getAllUsers() throws ServiceException {
    log.info("get all users");
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/user")
  public void createUser(@RequestBody UserDTO userDTO) throws ServiceException {
    log.info("add new user with email {}", userDTO.getEmailAddress());
    userService.add(userDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/user")
  public void updateUser(@RequestBody UserDTO userDTO) throws ServiceException {
    log.info("update user with email {}", userDTO.getEmailAddress());
    userService.update(userDTO);
  }

  @DeleteMapping(value = "/user/{email}")
  public void deleteUser(@PathVariable String email) throws ServiceException {
    log.info("delete User with email {}", email);
    userService.delete(email);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/user/{id}")
  public void changeLockStatus(@PathVariable long id, @RequestBody boolean lockStatus)
      throws ServiceException {
    log.info("change lock status to {} in user with id {}", lockStatus, id);
    userService.changeLockStatus(id, lockStatus);
  }
}
