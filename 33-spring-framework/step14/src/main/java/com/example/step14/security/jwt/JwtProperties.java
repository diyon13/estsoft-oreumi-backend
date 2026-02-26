package com.example.step14.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String secretKey,
        Duration accessTokenExpiration,
        Duration refreshTokenExpiration
) { }

/*
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
@Component
public class JwtProperties {
    // @Value("${jwt.secret-key}")
    private String secretKey;
    private Duration accessTokenExpiration;
    private Duration refreshTokenExpiration;
}
*/