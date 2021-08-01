package com.manhcode.rest.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceException {

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetail usernameNotFoundExcetion(UserNameNotFoundException ex) {
        return new CustomErrorDetail(new Date(), "From @RestControllerAdvice Not Found", ex.getMessage());
    }
}
