package com.infinite.api.member.service.impl;

import com.infinite.api.exception.memberException.AlreadyExistsMemberException;
import com.infinite.api.exception.memberException.MemberNotFound;
import com.infinite.api.exception.memberException.MemberPasswordDifferent;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.dto.MemberInfoDto;
import com.infinite.api.member.repository.MemberRepository;
import com.infinite.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.infinite.api.exception.memberException.memberEnum.MemberEnum.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFound(MEMBER_NOT_FOUND));
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
