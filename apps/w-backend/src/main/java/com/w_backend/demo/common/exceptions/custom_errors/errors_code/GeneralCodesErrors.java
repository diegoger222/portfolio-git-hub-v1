package com.w_backend.demo.common.exceptions.custom_errors.errors_code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneralCodesErrors {

    CREATE_ERROR_CODE(200),
    UPDATE_ERROR_CODE(201),
    DELETE_ERROR_CODE(202),

    // HTTP Status Codes
    NOT_FOUND_ERROR_CODE(404),
    UNAUTHORIZED_ERROR_CODE(401),
    FORBIDDEN_ERROR_CODE(403),
    INTERNAL_SERVER_ERROR_CODE(500),
    BAD_REQUEST_ERROR_CODE(400),;

    private final int code;
}
