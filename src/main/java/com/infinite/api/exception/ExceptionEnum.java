package com.infinite.api.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다."),
    NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "비밀번호 일치하지 않습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ExceptionEnum(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
