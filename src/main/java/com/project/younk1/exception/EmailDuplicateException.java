package com.project.younk1.exception;

import com.project.younk1.dto.ErrorCode;
import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException {

    private ErrorCode errorCode;

    public EmailDuplicateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;


    }
}
