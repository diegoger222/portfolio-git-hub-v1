package com.w_backend.demo.common.exceptions.custom_errors.errors;

import org.springframework.http.HttpStatus;

import com.w_backend.demo.common.exceptions.custom_errors.errors_code.CodesErrors;

import lombok.Getter;

@Getter
public class CreateException extends CustomException {

    private final String code;
    private final String module;
    private final HttpStatus statusCode;

    public CreateException(CodesErrors codesErrors, HttpStatus status) {
        super(codesErrors, status);
        this.code = codesErrors.getCode();
        this.module = codesErrors.getModule();
        this.statusCode = status;
    }

}
