package com.epam.hw_6.service.exeption;

public class ControllerException extends RuntimeException {
  public ControllerException(String message) {
    super(message);
  }
}
