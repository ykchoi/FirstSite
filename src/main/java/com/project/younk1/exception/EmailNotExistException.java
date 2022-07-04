package com.project.younk1.exception;

import com.project.younk1.dto.ErrorCode;
import lombok.Getter;

@Getter
public class EmailNotExistException extends RuntimeException {

    private ErrorCode errorCode;

    public EmailNotExistException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
