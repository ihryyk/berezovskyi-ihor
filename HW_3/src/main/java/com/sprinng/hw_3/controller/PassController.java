package com.sprinng.hw_3.controller;

import com.sprinng.hw_3.controller.dto.BookDTO;
import com.sprinng.hw_3.controller.dto.PassDTO;
import com.sprinng.hw_3.model.enums.PassStatus;
import com.sprinng.hw_3.service.PassService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/pass")
public class PassController {

  private final PassService passService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<PassDTO> getAllPasses() throws ServiceException {
    log.info("get all passes");
    return passService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "userId")
  public List<PassDTO> getPassesByUserId(@RequestParam("userId") long userId)
      throws ServiceException {
    log.info("get all passes by user id {}", userId);
    return passService.getAllByUserId(userId);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}/books")
  public List<BookDTO> getBooksFromPassByPassId(@PathVariable long id) throws ServiceException {
    log.info("get books from pass by pass id {}", id);
    return passService.getBooksById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createPass(@RequestBody PassDTO passDTO) throws ServiceException {
    log.info("create new pass");
    passService.create(passDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updatePass(@RequestBody PassDTO passDTO) throws ServiceException {
    log.info("update pass");
    passService.update(passDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changePassStatus(@PathVariable long id, PassStatus passStatus) {
    log.info("change pass status to {} in pass with id {}", passStatus, id);
    passService.changePassStatus(id, passStatus);
  }
}
