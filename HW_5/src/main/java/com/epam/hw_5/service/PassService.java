package com.epam.hw_5.service;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.controller.dto.PassDTO;
import com.epam.hw_5.model.enums.PassStatus;
import com.epam.hw_5.service.exeption.ServiceException;

import java.util.List;

public interface PassService {
  public void changePassStatus(long passId, PassStatus passStatus) throws ServiceException;

  public List<BookDTO> getBooksById(long passId) throws ServiceException;

  public List<PassDTO> getAll() throws ServiceException;

  public List<PassDTO> getAllByUserId(long userID) throws ServiceException;

  public void save(PassDTO passDTO) throws ServiceException;

  public void updatePenalty(long id, int penalty) throws ServiceException;
}
