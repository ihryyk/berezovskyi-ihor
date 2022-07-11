package com.epam.hw_4.controller;

import com.epam.hw_4.controller.dto.BookDTO;
import com.epam.hw_4.controller.dto.PassDTO;
import com.epam.hw_4.model.enums.PassStatus;
import com.epam.hw_4.service.PassService;
import com.epam.hw_4.service.exeption.ControllerException;
import com.epam.hw_4.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Slf4j
@RequiredArgsConstructor
public class PassController {

  private final PassService passService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/pass/")
  public List<PassDTO> getAllPasses() throws ServiceException {
    try {
      log.info("get all passes");
      return passService.getAll();
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/pass/{userId}")
  public List<PassDTO> getPassesByUserId(@PathVariable long id) throws ServiceException {
    try {
      log.info("get all passes by user id {}", id);
      return passService.getAllByUserId(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/pass/{id}/books")
  public List<BookDTO> getBooksFromPassByPassId(@PathVariable long id) throws ServiceException {
    try {
      log.info("get books from pass by pass id {}", id);
      return passService.getBooksById(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/pass")
  public void createPass(@RequestBody PassDTO passDTO) throws ServiceException {
    try {
      log.info("create new pass");
      passService.create(passDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = "/pass")
  public void updatePass(@RequestBody PassDTO passDTO) throws ServiceException {
    try {
      log.info("update pass");
      passService.update(passDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/pass/{id}")
  public void changePassStatus(@PathVariable long id, PassStatus passStatus) {
    try {
      log.info("change pass status to {} in pass with id {}", passStatus, id);
      passService.changePassStatus(id, passStatus);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
