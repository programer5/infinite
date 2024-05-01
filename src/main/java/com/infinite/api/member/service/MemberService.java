package com.infinite.api.member.service;

import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;

import java.util.List;

public interface MemberService {
    Long signUp(MemberInfoDto memberInfo);
    Member getMember(Long id);
    List<Member> getMembers();
}
