package com.example.step14.security.jwt;

import com.example.step14.domain.UserDto;
import com.example.step14.domain.UserRole;
import com.example.step14.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * HTTP 요청의 Authorization 헤더에서 JWT를 추출해서 검증하고,
 * 유효하면 Authentication 객체를 생성해서 SecurityContext에 저장하는 필터
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // HTTP 요청 헤더의 Authorization 헤더에서 JWT를 추출
        String header = request.getHeader("Authorization");

        // Authorization 헤더가 없거나 Authorization 헤더가 "Bearer "로 시작하지 않으면 인증 중단
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 "Bearer " 이후에 있는 JWT를 추출
        String token = header.replace("Bearer ", "");

        // JWT의 유효성을 검사하는 과정에서 예외가 발생하면 인증 오류 응답
        try {
            // JWT 만료 여부 확인
            if (jwtProvider.isExpired(token)) {
                // 401 Unauthorized 응답
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token has expired");
                return;
            }

            // JWT에서 사용자 정보 추출
            String username = jwtProvider.getUsername(token);
            UserRole userRole = UserRole.valueOf(
                    jwtProvider.getAuthority(token).replace("ROLE_", "")
            );

            // Authentication 인스턴스 생성
            UserDto userDto = UserDto.builder()
                    .username(username)
                    .password("N/A")
                    .role(userRole)
                    .build();

            UserDetails userDetails = CustomUserDetails.of(userDto);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            // SecurityContext에 인증 정보 저장
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            // 401 Unauthorized 응답
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }

        // SecurityFilterChain의 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }
}