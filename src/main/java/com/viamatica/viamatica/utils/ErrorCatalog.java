package com.viamatica.viamatica.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    USER_NOT_FOUND("ERR_USER_001", "User not found"),
    ROLE_NOT_FOUND("ERR_ROLE_001", "Role not found"),
    SESSION_NOT_FOUND("ERR_SES_001", "Session not found"),
    PERSON_NOT_FOUND("ERR_PER_001", "Person not found"),
    USER_INVALID("ERR_USER_002", "Invalid user parameters"),
    GENERIC_ERROR("ERR_GEN_001", "An error occurred"),
    VALIDATION_ERROR("ERR_VAL_001", "Validation error"),
    ACCESS_DENIED("ERR_ACC_001", "Access denied");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
