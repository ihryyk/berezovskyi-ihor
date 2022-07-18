package com.epam.hw_5.service.impl;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.controller.dto.PassDTO;
import com.epam.hw_5.controller.mapper.BookMapper;
import com.epam.hw_5.controller.mapper.PassMapper;
import com.epam.hw_5.model.entity.Pass;
import com.epam.hw_5.model.enums.PassStatus;
import com.epam.hw_5.repository.PassRepository;
import com.epam.hw_5.service.PassService;
import com.epam.hw_5.service.exeption.RepositoryException;
import com.epam.hw_5.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

  private final PassRepository passRepository;

  @Override
  @Transactional
  public void changePassStatus(long id, PassStatus passStatus) throws RepositoryException {
    log.info("change pass status to {} in pass with id {}", passStatus, id);
    Pass pass =
        passRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    pass.setPassStatus(passStatus);
    passRepository.save(pass);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BookDTO> getBooksById(long id) throws ServiceException {
    log.info("get books from pass by pass id {}", id);
    Pass pass =
        passRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    return pass.getOrder().getOrderBooks().stream().map(BookMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<PassDTO> getAll() {
    log.info("get all passes");
    return passRepository.findAll().stream().map(PassMapper.INSTANCE::mapToDto).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<PassDTO> getAllByUserId(long userID) {
    log.info("get all passes by user id {}", userID);
    return passRepository.getAllByUserId(userID).stream()
        .map(PassMapper.INSTANCE::mapToDto)
        .toList();
  }

  @Override
  public void save(PassDTO passDTO) {
    log.info("save pass");
    Pass pass = PassMapper.INSTANCE.mapToEntity(passDTO);
    passRepository.save(pass);
  }

  @Override
  @Transactional
  public void updatePenalty(long id, int penalty) throws RepositoryException {
    Pass pass =
        passRepository
            .findById(id)
            .orElseThrow(() -> new RepositoryException("Pass with id " + id + " not found"));
    pass.setPenalty(penalty);
    log.info("update penalty to {} in pass with id {}", penalty, id);
    passRepository.save(pass);
  }
}
