package com.leverx.app.aop;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.client.HttpClientErrorException.*;
import static org.springframework.web.client.HttpServerErrorException.*;


@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<Object> unauthorizedException() {
        return status(UNAUTHORIZED).build();
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Object> notFoundException() {
        return status(NOT_FOUND).build();
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> internalException() {
        return status(INTERNAL_SERVER_ERROR).build();
    }

}
