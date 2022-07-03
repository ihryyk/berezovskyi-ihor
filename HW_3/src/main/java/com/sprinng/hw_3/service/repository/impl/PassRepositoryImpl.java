package com.sprinng.hw_3.service.repository.impl;


import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.model.entity.Pass;
import com.sprinng.hw_3.model.enums.PassStatus;
import com.sprinng.hw_3.service.exeption.RepositoryExeption;
import com.sprinng.hw_3.service.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Repository
public class PassRepositoryImpl implements PassRepository {

    @Override
    public void changePassStatus(long passId, PassStatus passStatus) throws RepositoryExeption {

    }

    @Override
    public List<Book> getBooksById(long passId) throws RepositoryExeption {
        return null;
    }

    @Override
    public void create(Pass pass) throws RepositoryExeption {

    }

    @Override
    public List<Pass> getAll() throws RepositoryExeption {
        return null;
    }

    @Override
    public List<Pass> getAllByUserId(long userID) throws RepositoryExeption {
        return null;
    }

    @Override
    public void update(Pass pass) throws RepositoryExeption {

    }

    @Override
    public void updatePenalty(long id, int penalty) throws RepositoryExeption {

    }
}
