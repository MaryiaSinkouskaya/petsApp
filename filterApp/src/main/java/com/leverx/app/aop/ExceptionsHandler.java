package com.leverx.app.aop;


import com.leverx.app.exceptions.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<HttpClientErrorException> handleHttpClientErrorException(
            final HttpClientErrorException exception) {
        return new ResponseEntity<>(exception, exception.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<HttpServerErrorException> handleHttpServerErrorException(
            final HttpServerErrorException exception) {
        return new ResponseEntity<>(exception, exception.getStatusCode());
    }

    @ExceptionHandler(value = {InternalError.class, RestClientException.class})
    public ResponseEntity<RestClientException> handleInternalException(
            final RestClientException exception) {
        return new ResponseEntity<>(exception, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<NullPointerException> handleNullPointerException(
            final NullPointerException exception) {
        return new ResponseEntity<>(exception, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<RepositoryException> handleRepositoryException(
            final RepositoryException exception) {
        return new ResponseEntity<>(exception, INTERNAL_SERVER_ERROR);
    }
}
