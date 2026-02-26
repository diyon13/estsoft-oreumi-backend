package com.example.step14.security.jwt;

import com.example.step14.domain.JwtResponseDto;
import com.example.step14.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

/**
 * JWT 유틸리티를 제공
 */
@Component
@Slf4j
public class JwtProvider {
    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;

    /**
     * 외부 설정을 바인딩한 JwtProperties를 주입받아 JWT에 서명하는 SecretKey 인스턴스를 생성
     */
    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        secretKey = new SecretKeySpec(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());

        log.info("JWT secret-key: {}", jwtProperties.secretKey());
    }

    /**
     * JWT에서 username을 추출
     */
    public String getUsername(String token) {
        /*
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("username", String.class);
        */

        return getClaim(token, "username");
    }

    /**
     * JWT에서 권한(authority)을 추출
     */
    public String getAuthority(String token) {
        return getClaim(token, "authority");
    }

    /**
     * JWT에서 지정한 클레임(claim)을 추출
     */
    public String getClaim(String token, String claim) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(claim, String.class);
    }

    /**
     * JWT의 만료 여부를 확인
     */
    public boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    /**
     * username과 권한(authority), 만료 시간(expiration)을 포함하는 Access 토큰 발급
     */
    public String issueAccessToken(String username, String authority) {
        return Jwts.builder()
                .claim("username", username)
                .claim("authority", authority)
                .issuedAt(new Date())
                .expiration(getDateAfterDuration(jwtProperties.accessTokenExpiration()))
                .signWith(secretKey)
                .compact();
    }

    /**
     * username과 만료 시간(expiration)을 포함하는 Refresh 토큰 발급
     */
    public String issueRefreshToken(String username) {
        return Jwts.builder()
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(getDateAfterDuration(jwtProperties.refreshTokenExpiration()))
                .signWith(secretKey)
                .compact();
    }

    /**
     * 현재 시각에서 duration이 지난 시각을 생성
     */
    private Date getDateAfterDuration(Duration duration) {
        return new Date(new Date().getTime() + duration.toMillis());
    }

    /**
     * Authentication의 정보로 JwtResponseDto를 생성
     */
    public JwtResponseDto getJwtResponseDto(Authentication authentication) {
        // Authentication에서 CustomUserDetails를 조회
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // CustomUserDetails에서 username을 조회
        assert userDetails != null;
        String username = userDetails.getUsername();

        // CustomUserDetails에서 사용자 권한(authority)을 조회
        String authority = userDetails.getAuthorities().iterator().next().getAuthority();

        // Access 토큰과 Refresh 토큰 발급
        String accessToken = issueAccessToken(username, authority);
        String refreshToken = issueRefreshToken(username);

        // JwtResponseDto 생성
        return JwtResponseDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}