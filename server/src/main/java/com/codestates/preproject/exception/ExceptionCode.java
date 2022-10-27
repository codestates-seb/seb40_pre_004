package com.codestates.preproject.exception;

import lombok.Getter;

public enum ExceptionCode {
    // Member 관련
    MEMBER_NOT_FOUND(404, "MEMBER NOT FOUND"),
    MEMBER_EXISTS(409, "MEMBER_EXISTS"),
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED"),
    HANDLE_ACCESS_DENIED(403,"HANDLE ACCESS DENIED"),
    INVALID_INPUT_VALUE(400,  "INVALID INPUT VALUE"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}