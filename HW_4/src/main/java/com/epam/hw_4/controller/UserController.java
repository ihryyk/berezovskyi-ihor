package com.epam.hw_4.controller;


import com.epam.hw_4.controller.dto.UserDTO;
import com.epam.hw_4.service.UserService;
import com.epam.hw_4.service.exeption.ControllerException;
import com.epam.hw_4.service.exeption.ServiceException;
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
        try {
            log.info("get user by email{}", email);
            return userService.getByEmail(email);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    public List<UserDTO> getAllUsers() throws ServiceException {
        try {
            log.info("get all users");
            return userService.getAll();
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public void createUser(@RequestBody UserDTO userDTO) throws ServiceException {
        try {
            log.info("add new user with email {}", userDTO.getEmailAddress());
            userService.add(userDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user")
    public void updateUser(@RequestBody UserDTO userDTO) throws ServiceException {
        try {
            log.info("update user with email {}", userDTO.getEmailAddress());
            userService.update(userDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }


    @DeleteMapping(value = "/user/{email}")
    public void deleteUser(@PathVariable String email) throws ServiceException {
        try {
            log.info("delete User with email {}", email);
            userService.delete(email);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/user/{id}")
    public void changeLockStatus(@PathVariable long id, @RequestBody boolean lockStatus) throws ServiceException {
        try {
            log.info("change lock status to {} in user with id {}", lockStatus, id);
            userService.changeLockStatus(id, lockStatus);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }
}
