package com.manhcode.rest.demo.exception;

public class UserNameNotFoundException extends RuntimeException{
    public UserNameNotFoundException(String message) {
        super(message);
    }
}
