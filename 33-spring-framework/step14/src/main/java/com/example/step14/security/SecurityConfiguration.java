package com.example.step14.security;

import com.example.step14.security.jwt.JwtAuthenticationFilter;
import com.example.step14.security.jwt.JwtProvider;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;
    private final AuthenticationConfiguration authenticationConfiguration;

    /**
     * UserService에서 로그인을 처리하기 위해 AuthenticationManager를 빈(bean)으로 등록
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Spring Security 프레임워크의 SecurityFilterChain을 적용하지 않을 요청을 전역적으로 지정
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers("/h2-console/**")
                .requestMatchers("/static/**");
    }

    /**
     * "/api/**"로 요청이 들어온 경우 적용할 SecurityFilterChain
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .securityMatcher("/api/**")
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/user/login", "/api/user/signup").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)         // 세션을 저장하지 않음
                );

        return httpSecurity.build();
    }

    /**
     * SecurityFilterChain의 구성을 설정
     */
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Credential 기반 인증(username과 password로 인증)에 대한 설정
        httpSecurity
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                );

        // 인가(authorization)에 대한 설정
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/", "/login", "/signup").permitAll()
                        .requestMatchers("/board/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/error/**").permitAll()
                        .anyRequest().authenticated()
                );

        // AccessDeniedException 예외를 처리할 핸들러 등록
        httpSecurity
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(accessDeniedHandler())
                );

        // CustomUserDetailsService를 UserDetailsService 등록
        httpSecurity
                .userDetailsService(customUserDetailsService);

        return httpSecurity.build();
    }

    /**
     * AccessDeniedException 예외가 발생했을 때 처리하기 위한 핸들러
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) ->
                request.getRequestDispatcher("/error/403").forward(request, response);
    }

    /**
     * 사용자가 입력한 비밀번호를 암호화할 PasswordEncoder를 빈(bean)으로 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * CORS(Cross-Origin Resource Sharing)를 설정하는 빈(bean)을 등록
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();

            // 허용할 도메인
            // corsConfiguration.addAllowedOrigin("*");
            corsConfiguration.setAllowedOrigins(Collections.singletonList("http:localhost:8080"));

            // 모든 HTTP 요청 방식 허용
            corsConfiguration.setAllowedMethods(Collections.singletonList("*"));

            // 인증 정보 포함 허용
            corsConfiguration.setAllowCredentials(true);

            // 모든 헤더 허용
            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

            // Authorization 헤더 노출
            corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));

            // 1시간 동안 캐싱
            corsConfiguration.setMaxAge(3600L);

            return corsConfiguration;
        };
    }
}