package com.leverx.app.aop;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.client.HttpClientErrorException.NotFound;
import static org.springframework.web.client.HttpClientErrorException.Unauthorized;
import static org.springframework.web.client.HttpServerErrorException.InternalServerError;


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
