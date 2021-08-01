package com.manhcode.rest.demo.exception;

public class UserExistException extends RuntimeException {
    public UserExistException (String message) {
        super(message);
    }
}
