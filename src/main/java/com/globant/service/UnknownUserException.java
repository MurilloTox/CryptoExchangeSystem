package com.globant.service;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String message) {
        super(message);
    }
}
