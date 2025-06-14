package com.w_backend.demo.common.exceptions.custom_errors.errors;

import org.springframework.http.HttpStatus;

import com.w_backend.demo.common.exceptions.custom_errors.errors_code.CodesErrors;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final String code;
    private final String module;
    private final HttpStatus statusCode;

    public CustomException(CodesErrors codesErrors) {
        super(codesErrors.getMessage());
        this.code = codesErrors.getCode();
        this.module = codesErrors.getModule();
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR; // Default status code
    }

    public CustomException(CodesErrors codesErrors, HttpStatus status) {
        super(codesErrors.getMessage());
        this.code = codesErrors.getCode();
        this.module = codesErrors.getModule();
        this.statusCode = status;
    }

}
