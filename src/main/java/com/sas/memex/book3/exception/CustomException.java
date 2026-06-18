package com.sas.memex.book3.exception;

public class CustomException extends RuntimeException {

    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
