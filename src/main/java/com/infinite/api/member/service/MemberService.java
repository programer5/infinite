package com.infinite.api.member.service;

import com.infinite.api.member.dto.MemberInfoDto;

public interface MemberService {
    Long signUp(MemberInfoDto memberInfo);
}
