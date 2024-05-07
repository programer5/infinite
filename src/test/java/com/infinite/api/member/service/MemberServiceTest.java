package com.infinite.api.member.service;

import com.infinite.api.exception.memberException.MemberNotFound;
import com.infinite.api.exception.memberException.memberEnum.MemberEnum;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

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

        Long memberId = memberService.signUp(memberInfoDto);

        Assertions.assertNotNull(memberId);
    }

    @Test
    @DisplayName("회원 1명 조회")
    void getMember() {

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Member member = memberInfoDto.getMemberEntity();

        Member saveMember = memberRepository.save(member);

        memberService.getMember(saveMember.getId());

        Member getMember = memberRepository.findById(saveMember.getId())
                .orElseThrow(() -> new MemberNotFound(MemberEnum.MEMBER_NOT_FOUND));

        Assertions.assertEquals(saveMember.getId(), getMember.getId());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void getMembers() {

        for (int i = 0; i < 10; i++) {
            MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                    .email("neverdie4757@gmail.com" + i)
                    .password("1234" + i)
                    .build();

            Member member = memberInfoDto.getMemberEntity();

            memberRepository.save(member);
        }

        List<Member> members = memberService.getMembers();

        Assertions.assertEquals(10, members.size());
    }

    @Test
    @DisplayName("로그인")
    void signIn() {
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Member member = memberInfoDto.getMemberEntity();

        memberRepository.save(member);

        Long memberId = memberService.signIn(memberInfoDto);

        Assertions.assertEquals(1L, memberId);
    }

    @Test
    @DisplayName("로그인 시 존재하지 않는 이메일")
    void signInNotExistEmail() {
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email("neverdie4757@gmail.com")
                .password("1234")
                .build();

        Member member = memberInfoDto.getMemberEntity();

        memberRepository.save(member);

        MemberInfoDto request = MemberInfoDto.builder()
                .email("neverdie4757123@gmail.com")
                .password("1234")
                .build();

        Assertions.assertThrows(MemberNotFound.class, () -> memberService.signIn(request));
    }
}