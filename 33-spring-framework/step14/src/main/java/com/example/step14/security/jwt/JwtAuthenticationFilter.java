package com.example.step14.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * HTTP 요청의 Authorization 헤더에서 JWT를 추출해서 검증하고,
 * 유효하면 Authentication 객체를 생생해서 SecurityContext에 저장하는 필터
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //HTTP 요청 헤더의 Authorization 헤더에서 JWT를 추출
        String header = request.getHeader("Authorization");

        // Authorization 헤더가 업석나 Authorization 헤더가 "Bearer "로 시작하지 않으면 인증 중단
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 "Bearer " 이후에 있는 JWT를 추출
        String token = header.replace("Bearer ", "");

        // JWT의 유효성을 검사하는 과정에서 예외가 발생하면 인증 오류 응답
        try {

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            // 401 Unauthorized 응답
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }

        // SecurityFilterChain의 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }
}
