package com.infinite.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResult {

    private String status;
    private String message;
    private ApiExceptionEntity exception;

    @Builder
    public ApiResult(String status, String message, ApiExceptionEntity exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }
}
