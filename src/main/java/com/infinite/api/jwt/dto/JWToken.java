package com.infinite.api.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JWToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public JWToken(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
