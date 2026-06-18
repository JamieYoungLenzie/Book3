package com.sas.memex.book3.exception;

import com.sas.memex.book3.model.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String BAD_CREDENTIALS = "Invalid credentials";

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<AppError> handleCustomException(CustomException ex) {
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AppError(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppError> handleBadCredentialsException(BadCredentialsException ex) {
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppError(BAD_CREDENTIALS));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AppError> handleIllegalArgumentException(IllegalArgumentException ex) {
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppError(ex.getMessage()));
    }
}
