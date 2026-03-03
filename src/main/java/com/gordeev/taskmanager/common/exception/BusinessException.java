package com.gordeev.taskmanager.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private String errorCode;
    private HttpStatus httpStatus;

    public BusinessException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }
}
