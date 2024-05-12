package com.infinite.api.member.dto;

import com.infinite.api.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoDto {

    private final String email;
    private final String password;

    @Builder
    public MemberInfoDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
