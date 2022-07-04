package com.project.younk1.dto.response;

import com.project.younk1.dto.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ErrorResponse {

    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
