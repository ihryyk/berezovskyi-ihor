package com.sprinng.hw_3.service.impl;


import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.controller.dto.PassDTO;
import com.sprinng.hw_3.controller.mapper.BookMapper;
import com.sprinng.hw_3.controller.mapper.PassMapper;
import com.sprinng.hw_3.model.entity.Pass;
import com.sprinng.hw_3.model.enums.PassStatus;
import com.sprinng.hw_3.service.PassService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import com.sprinng.hw_3.service.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

    private final PassRepository passRepository;

    @Override
    public void changePassStatus(long id, PassStatus passStatus) throws ServiceException {
        log.info("change pass status to {} in pass with id {}", passStatus, id);
        passRepository.changePassStatus(id, passStatus);
    }

    @Override
    public List<BookDTO> getBooksById(long id) throws ServiceException {
        log.info("get books from pass by pass id {}", id);
        return passRepository.getBooksById(id).stream().map(BookMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public void create(PassDTO passDTO) throws ServiceException {
        log.info("create new pass");
        Pass pass = PassMapper.INSTANCE.mapToEntity(passDTO);
        passRepository.create(pass);
    }

    @Override
    public List<PassDTO> getAll() throws ServiceException {
        log.info("get all passes");
        return passRepository.getAll().stream().map(PassMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public List<PassDTO> getAllByUserId(long userID) throws ServiceException {
        log.info("get all passes by user id {}", userID);
        return passRepository.getAllByUserId(userID).stream().map(PassMapper.INSTANCE::mapToDto).toList();
    }

    @Override
    public void update(PassDTO passDTO) throws ServiceException {
        log.info("update pass");
        Pass pass = PassMapper.INSTANCE.mapToEntity(passDTO);
        passRepository.update(pass);
    }

    @Override
    public void updatePenalty(long id, int penalty) throws ServiceException {
        log.info("update penalty to {} in pass with id {}", penalty, id);
        passRepository.updatePenalty(id, penalty);
    }
}
