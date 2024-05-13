package com.infinite.api.auth.service;

import com.infinite.api.exception.memberException.AlreadyExistsMemberException;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import com.infinite.api.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 시 이미 존재하는 회원")
    void SignUpAlreadyExistsMember() {

        Member member = Member.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        memberRepository.save(member);

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        assertThrows(AlreadyExistsMemberException.class, () -> authService.signUp(memberInfoDto));

    }

    @Test
    @DisplayName("회원가입")
    void signUp() {

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Long memberId = authService.signUp(memberInfoDto);

        assertNotNull(memberId);
    }

    @Test
    @DisplayName("회원 가입 시 비밀번호 암호화")
    void encodePassword() {

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Long memberId = authService.signUp(memberInfoDto);

        Member member = memberService.getMember(memberId);

        boolean matches = passwordEncoder.matches(memberInfoDto.getPassword(), member.getPassword());
        assertNotNull(memberId);
        assertEquals(Boolean.TRUE, matches);
    }
}