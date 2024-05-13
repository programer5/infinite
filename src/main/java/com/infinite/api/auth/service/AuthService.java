package com.infinite.api.auth.service;

import com.infinite.api.member.dto.MemberInfoDto;

public interface AuthService {
    Long signUp(MemberInfoDto memberInfo);
    Long signIn(MemberInfoDto memberInfo);
}
