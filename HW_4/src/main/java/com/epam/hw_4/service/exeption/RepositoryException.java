package com.epam.hw_4.service.exeption;

public class RepositoryException extends RuntimeException{
    public RepositoryException(String message) {
        super(message);
    }
}
