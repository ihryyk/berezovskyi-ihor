package com.epam.hw_4.service.exeption;

public class ControllerException extends RuntimeException{
    public ControllerException(String message) {
        super(message);
    }

}
