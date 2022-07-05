package com.sprinng.hw_3.service.impl;


import com.sprinng.hw_3.controller.dto.UserDTO;
import com.sprinng.hw_3.controller.mapper.UserMapper;
import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.UserService;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.service.exeption.ServiceException;
import com.sprinng.hw_3.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void add(UserDTO userDTO) throws ServiceException {
        log.info("add new user with email {}", userDTO.getEmailAddress());
        User user = UserMapper.INSTANCE.mapToEntity(userDTO);
        userRepository.add(user);
    }

    @Override
    public void update(UserDTO userDTO) throws ServiceException {
        log.info("update user with email {}", userDTO.getEmailAddress());
        User user = UserMapper.INSTANCE.mapToEntity(userDTO);
        userRepository.update(user);
    }

    @Override
    public UserDTO getByEmailAndPassword(String email, String password) throws ServiceException {

        User user = userRepository.getByEmailAndPassword(email, password);
        if (user == null) {
            throw new ServiceException(format("User with email %s and password %s not found", email, password));
        }
        log.info("get user by email{} and password {}", user.getEmailAddress(), user.getPassword());
        return UserMapper.INSTANCE.mapToDto(user);
    }

    @Override
    public UserDTO getByEmail(String email) throws ServiceException {
        try {
            User user = userRepository.getByEmail(email);
            log.info("get user by email{}", user.getEmailAddress());
            if (user == null) {
                throw new ServiceException(format("User with email %s not found", email));
            }
            return UserMapper.INSTANCE.mapToDto(user);
        } catch (RepositoryExeption exeption) {
            throw new ServiceException();
        }
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        log.info("get all users");
        return userRepository.getAll().stream().map(UserMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public void changeLockStatus(long id, boolean lockStatus) throws ServiceException {
        log.info("change lock status to {} in user with id {}", lockStatus, id);
        userRepository.changeLockStatus(id, lockStatus);
    }

    @Override
    public void delete(String email) throws ServiceException {
        log.info("delete User with email {}", email);
        userRepository.delete(email);
    }
}
