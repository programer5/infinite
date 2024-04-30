package com.infinite.api.member.service.impl;

import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import com.infinite.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long signUp(MemberInfoDto memberInfo) {

        Member member = memberInfo.getMemberEntity();

        Member saveMember = memberRepository.save(member);

        return saveMember.getId();
    }
}
