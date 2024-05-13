package com.infinite.api.auth.service.impl;

import com.infinite.api.auth.service.AuthService;
import com.infinite.api.exception.memberException.AlreadyExistsMemberException;
import com.infinite.api.exception.memberException.MemberNotFound;
import com.infinite.api.exception.memberException.MemberPasswordDifferent;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.infinite.api.exception.memberException.memberEnum.MemberEnum.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(MemberInfoDto memberInfo) {

        Optional<Member> existingMember = memberRepository.findMemberByEmail(memberInfo.getEmail());

        if (existingMember.isPresent()) {
            throw new AlreadyExistsMemberException(ALREADY_EXISTS_MEMBER);
        }

        String encodedPassword = passwordEncoder.encode(memberInfo.getPassword());

        Member member = Member.builder()
                .email(memberInfo.getEmail())
                .password(encodedPassword)
                .build();

        Member saveMember = memberRepository.save(member);

        return saveMember.getId();
    }

    @Override
    public Long signIn(MemberInfoDto memberInfo) {
        Member member = memberRepository.findMemberByEmail(memberInfo.getEmail())
                .orElseThrow(() -> new MemberNotFound(MEMBER_NOT_FOUND));

        if (member.getPassword().equals(memberInfo.getPassword())) {
            return member.getId();
        } else {
            throw new MemberPasswordDifferent(MEMBER_PASSWORD_DIFFERENT);
        }
    }
}
