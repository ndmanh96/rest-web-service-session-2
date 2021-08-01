package com.manhcode.rest.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalHandlerExeption extends ResponseEntityExceptionHandler {

    //agument is not valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail customErrorDetail =
                new CustomErrorDetail(new Date(), "From Method ArgumentNotValid Exception", request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
    }

    // Method not allow


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail customErrorDetail =
                new CustomErrorDetail(new Date(), "From handleHttpRequestMethodNotSupported", request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        CustomErrorDetail customErrorDetail =
                new CustomErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetail, HttpStatus.BAD_REQUEST);
    }

    //UserNamenotfound excception
    @ExceptionHandler(UserNameNotFoundException.class)
    protected ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorDetail customErrorDetail =
                new CustomErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(customErrorDetail, HttpStatus.NOT_FOUND);
    }
}
