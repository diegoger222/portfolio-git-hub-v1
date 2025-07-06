package com.w_backend.demo.common.exceptions.custom_errors.errors_code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodesErrors {
    // error codes for the application

    // Category Errors
    CATEGORY_NOT_FOUND("C001", "category", "Category not found"),
    CATEGORY_ALREADY_EXISTS("C002", "category", "Category already exists"),;

    private final String code;
    private final String module;
    private final String message;

}