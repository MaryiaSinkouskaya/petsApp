package com.leverx.app.exceptions;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String errorMessage) {
        super(errorMessage);
    }
}
