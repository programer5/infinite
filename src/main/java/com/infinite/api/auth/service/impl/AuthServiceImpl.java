package com.infinite.api.auth.service.impl;

import com.infinite.api.auth.dto.LoginRequestDto;
import com.infinite.api.auth.dto.MemberInfoDto;
import com.infinite.api.auth.service.AuthService;
import com.infinite.api.exception.ApiException;
import com.infinite.api.exception.ExceptionEnum;
import com.infinite.api.jwt.JwtGenerator;
import com.infinite.api.jwt.dto.JWToken;
import com.infinite.api.member.domain.Member;
import com.infinite.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final JwtGenerator jwtGenerator;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public String login(LoginRequestDto request) {

        Member member = memberRepository.findMemberByEmail(request.getEmail()).orElseThrow(()
                -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new ApiException(ExceptionEnum.NOT_MATCH_PASSWORD);
        }

        MemberInfoDto memberInfo = modelMapper.map(member, MemberInfoDto.class);
        JWToken jwToken = jwtGenerator.generateToken(memberInfo.getId());

        return jwToken.getAccessToken();
    }
}
