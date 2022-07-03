package com.sprinng.hw_3.service.repository;


import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;

import java.util.List;

public interface UserRepository {

    public void add(User user) throws RepositoryExeption;

    public void update(User user) throws RepositoryExeption;

    public User getByEmailAndPassword(String email, String password) throws RepositoryExeption;

    public User getByEmail(String email) throws RepositoryExeption;

    public List<User> getAll() throws RepositoryExeption;

    public void changeLockStatus(long userId, boolean lockStatus) throws RepositoryExeption;

    public void delete(String email) throws RepositoryExeption;
}
