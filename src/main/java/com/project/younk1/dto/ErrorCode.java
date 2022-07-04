package com.project.younk1.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST(400, "C001", "Bad Request"),
    NOT_FOUND(404, "C002", "Not Found"),
    INTERNAL_ERROR(500, "C003", "Internal Server Error"),
    EMAIL_DUPLICATE(400, "M001", "Email Duplicate"),
    EMAIL_NOT_EXIST(400, "M002", "Email Not Exist"),
    PASSWORD_WRONG(400, "M003", "Password Wrong")
    ;

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
