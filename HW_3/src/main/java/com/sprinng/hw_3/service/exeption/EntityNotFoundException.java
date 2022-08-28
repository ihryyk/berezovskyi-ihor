package com.sprinng.hw_3.service.exeption;

public class EntityNotFoundException extends ServiceException {

    private static final String MESSAGE = "Entity is not found";

    public EntityNotFoundException() {
        super(MESSAGE);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}