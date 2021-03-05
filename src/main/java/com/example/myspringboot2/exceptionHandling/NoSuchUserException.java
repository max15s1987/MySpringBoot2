package com.example.myspringboot2.exceptionHandling;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
