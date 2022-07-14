package com.epam.hw_4.service.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandlerException {
  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception handleServiceException(ServiceException ex) {
    log.error("ServiceException: exception {}", ex.getMessage(), ex);
    return new Exception(ex.getMessage());
  }

  @ExceptionHandler(ControllerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception handleControllerException(ControllerException ex) {
    log.error("ControllerException: exception {}", ex.getMessage(), ex);
    return new Exception(ex.getMessage());
  }

  @ExceptionHandler(RepositoryException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Exception handleRepositoryException(RepositoryException ex) {
    log.error("RepositoryException: exception {}", ex.getMessage(), ex);
    return new Exception(ex.getMessage());
  }
}
