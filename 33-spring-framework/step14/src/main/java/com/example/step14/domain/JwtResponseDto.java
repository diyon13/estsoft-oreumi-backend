package com.example.step14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Access 토큰과 Refresh 토큰을 담아 클라이언트에 응답할 DTO
 */
@Data
@AllArgsConstructor
@Builder
public class JwtResponseDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}