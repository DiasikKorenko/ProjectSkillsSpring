package com.tms.exception;

public class BadRequestEx extends RuntimeException {
    public BadRequestEx(String message) {
        super(message);
    }
}
