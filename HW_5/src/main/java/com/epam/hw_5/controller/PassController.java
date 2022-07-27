package com.epam.hw_5.controller;

import com.epam.hw_5.controller.dto.BookDTO;
import com.epam.hw_5.controller.dto.PassDTO;
import com.epam.hw_5.model.enums.PassStatus;
import com.epam.hw_5.service.PassService;
import com.epam.hw_5.service.exeption.ControllerException;
import com.epam.hw_5.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/pass")
public class PassController {

  private final PassService passService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<PassDTO> getAllPasses() {
    log.info("get all passes");
    return passService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "userId")
  public List<PassDTO> getPassesByUserId(@RequestParam(name = "userId") long userId) {
    try {
      log.info("get all passes by user id {}", userId);
      return passService.getAllByUserId(userId);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}/books")
  public List<BookDTO> getBooksFromPassByPassId(@PathVariable long id) {
    try {
      log.info("get books from pass by pass id {}", id);
      return passService.getBooksByPassId(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createPass(@RequestBody @Valid PassDTO passDTO) {
    log.info("create new pass");
    passService.save(passDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updatePass(@RequestBody @Valid PassDTO passDTO) {
    log.info("update pass");
    passService.save(passDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changePassStatus(@PathVariable long id, @RequestBody PassStatus passStatus) {
    try {
      log.info("change pass status to {} in pass with id {}", passStatus, id);
      passService.changePassStatus(id, passStatus);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
