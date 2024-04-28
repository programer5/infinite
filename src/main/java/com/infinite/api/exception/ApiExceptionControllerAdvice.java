package com.infinite.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiResult> exceptionHandler(HttpServletRequest request, final ApiException e) {
        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .build();

        return ResponseEntity.status(e.getError().getStatus())
                .body(ApiResult.builder()
                        .status("error")
                        .message("")
                        .exception(apiExceptionEntity)
                        .build());
    }
}
