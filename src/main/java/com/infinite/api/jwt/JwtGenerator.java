package com.infinite.api.jwt;

import com.infinite.api.jwt.dto.JWToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {

    private final Key key;
    private static final String GRANT_TYPE = "Bearer";
    private static final int ACCESS_TOKEN_EXPIRE_TIME = 86400000;
    private static final int REFRESH_TOKEN_EXPIRE_TIME = 86400000;

    public JwtGenerator(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JWToken generateToken(Long userId) {
        long now = (new Date()).getTime();

        Date accessTokenExpireIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpireIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(accessTokenExpireIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JWToken.builder()
                .grantType(GRANT_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
