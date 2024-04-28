package com.infinite.api.auth.service;

import com.infinite.api.auth.dto.LoginRequestDto;

public interface AuthService {
    String login(LoginRequestDto request);
}
