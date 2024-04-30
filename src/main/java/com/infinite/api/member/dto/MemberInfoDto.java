package com.infinite.api.member.dto;

import com.infinite.api.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoDto {

    private String email;
    private String password;

    public Member getMemberEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberInfoDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
