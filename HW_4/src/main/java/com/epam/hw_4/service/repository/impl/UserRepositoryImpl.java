package com.epam.hw_4.service.repository.impl;


import com.epam.hw_4.model.entity.User;
import com.epam.hw_4.service.exeption.RepositoryException;
import com.epam.hw_4.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void add(User user) throws RepositoryException {

    }

    @Override
    public void update(User user) throws RepositoryException {

    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws RepositoryException {
        return null;
    }

    @Override
    public User getByEmail(String email) throws RepositoryException {
        return null;
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        return null;
    }

    @Override
    public void changeLockStatus(long userId, boolean lockStatus) throws RepositoryException {

    }

    @Override
    public void delete(String email) throws RepositoryException {

    }
}
