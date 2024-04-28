package com.infinite.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;

}
