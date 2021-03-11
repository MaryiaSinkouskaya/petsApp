package com.leverx.app.aop;


import com.leverx.app.exceptions.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> httpClientErrorException(HttpClientErrorException exception) {
        return status(exception.getStatusCode()).build();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> httpServerErrorException(HttpServerErrorException exception) {
        return status(exception.getStatusCode()).build();
    }

    @ExceptionHandler(value = {InternalError.class, RestClientException.class})
    public ResponseEntity<Object> internalException() {
        return status(INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointer() {
        return status(INTERNAL_SERVER_ERROR).body(NullPointerException.class);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Object> repoException() {
        return status(INTERNAL_SERVER_ERROR).body(RepositoryException.class);
    }

}
