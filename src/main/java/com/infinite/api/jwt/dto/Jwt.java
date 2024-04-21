package com.infinite.api.jwt.dto;

import lombok.Getter;

@Getter
public class Jwt {

    private String accessToken;
    private String refreshToken;
}
