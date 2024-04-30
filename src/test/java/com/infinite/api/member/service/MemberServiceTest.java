package com.infinite.api.member.service;

import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void signUp() {

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Member member = memberInfoDto.getMemberEntity();

        Member saveMember = memberRepository.save(member);

        Assertions.assertEquals(1L, saveMember.getId());
    }
}