package com.infinite.api.member;

import lombok.Getter;

@Getter
public enum RoleType {

    USER("USER", "일반회원"),
    ADMIN("ADMIN", "관리자");

    private final String role;
    private final String value;

    RoleType(String role, String value) {
        this.role = role;
        this.value = value;
    }
}
