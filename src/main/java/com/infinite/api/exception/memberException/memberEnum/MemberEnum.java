package com.infinite.api.exception.memberException.memberEnum;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberEnum {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.");

    private int code;
    private String message;

    MemberEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
