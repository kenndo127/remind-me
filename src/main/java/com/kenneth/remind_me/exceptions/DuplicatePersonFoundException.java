package com.kenneth.remind_me.exceptions;

public class DuplicatePersonFoundException extends RuntimeException {
    public DuplicatePersonFoundException(String message) {
        super(message);
    }
}
