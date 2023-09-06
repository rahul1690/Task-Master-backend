package com.freework.taskmaster.exceptions;

public class TeamAlreadyExistException extends RuntimeException {

    public TeamAlreadyExistException(String message) {
        super(message);
    }
}
