package com.infinite.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiExceptionEntity {

    private int code;
    private String message;

    @Builder
    public ApiExceptionEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
