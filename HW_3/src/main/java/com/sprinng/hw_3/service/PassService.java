package com.sprinng.hw_3.service;


import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.controller.dto.PassDTO;
import com.sprinng.hw_3.model.entity.Book;
import com.sprinng.hw_3.model.entity.Pass;
import com.sprinng.hw_3.model.enums.PassStatus;
import com.sprinng.hw_3.service.exeption.ServiceException;

import java.util.List;

public interface PassService {
    public void changePassStatus(long passId, PassStatus passStatus) throws ServiceException;
    public List<BookDTO> getBooksById(long passId) throws ServiceException;
    public void create(PassDTO passDTO) throws ServiceException;
    public List<PassDTO> getAll() throws ServiceException;
    public List<PassDTO> getAllByUserId(long userID) throws ServiceException;
    public void update(PassDTO passDTO) throws ServiceException;
    public void updatePenalty(long id, int penalty) throws ServiceException;

}
