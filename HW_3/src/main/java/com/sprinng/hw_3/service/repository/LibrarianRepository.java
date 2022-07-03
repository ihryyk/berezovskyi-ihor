package com.sprinng.hw_3.service.repository;


import com.sprinng.hw_3.model.entity.User;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;

import java.util.List;

public interface LibrarianRepository {
    public void create(User librarian) throws RepositoryExeption;
    public List<User> getAll() throws RepositoryExeption;
}
