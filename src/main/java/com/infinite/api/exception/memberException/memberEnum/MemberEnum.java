package com.infinite.api.exception.memberException.memberEnum;

import lombok.Getter;

@Getter
public enum MemberEnum {
    MEMBER_NOT_FOUND("M400", "존재하지 않는 회원입니다."),
    ALREADY_EXISTS_MEMBER("M401", "이미 존재하는 회원 아이디 입니다."),
    MEMBER_PASSWORD_DIFFERENT("M402", "비밀번호가 일치하지 않습니다.");

    private String code;
    private String message;

    MemberEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
