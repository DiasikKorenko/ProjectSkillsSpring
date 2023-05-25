package com.tms.exception;

public class ForbiddenEx extends RuntimeException {
    public ForbiddenEx(String message) {
        super(message);
    }
}