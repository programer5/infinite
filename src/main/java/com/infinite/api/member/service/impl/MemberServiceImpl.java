package com.infinite.api.member.service.impl;

import com.infinite.api.exception.memberException.MemberNotFound;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import com.infinite.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.infinite.api.exception.memberException.memberEnum.MemberEnum.MEMBER_NOT_FOUND;

@Slf4j
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

    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFound(MEMBER_NOT_FOUND));
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Long signIn(MemberInfoDto memberInfo) {
        Member member = memberRepository.findMemberByEmail(memberInfo.getEmail())
                .orElseThrow(() -> new MemberNotFound(MEMBER_NOT_FOUND));

        if (member.getPassword().equals(memberInfo.getPassword())) {
            log.info("정상적으로 로그인이 되었습니다.", member.getId());
            return member.getId();
        } else {
            log.info("비밀번호가 일치하지 않습니다..", member.getPassword());
            throw new MemberNotFound(MEMBER_NOT_FOUND);
        }
    }
}
