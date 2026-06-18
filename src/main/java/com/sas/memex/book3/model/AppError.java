package com.sas.memex.book3.model;

import org.springframework.stereotype.Component;

@Component
public class AppError {

    private String message;

    public AppError() {
    }

    public AppError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
