package com.example.step14.service;

import com.example.step14.domain.JwtResponseDto;
import com.example.step14.domain.LoginDto;
import com.example.step14.domain.UserDto;
import com.example.step14.domain.UserEntity;
import com.example.step14.persistence.UserRepository;
import com.example.step14.security.jwt.JwtProvider;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public void create(@NotNull UserDto userDto) {
        checkUsernameAvailability(userDto.getUsername());
        checkEmailAvailability(userDto.getEmail());

        setEncodedPassword(userDto);

        UserEntity savedEntity = userRepository.save(UserEntity.from(userDto));
        userDto.setId(savedEntity.getId());
    }

    @Override
    public Optional<UserDto> read(@NotNull Long id) {
        return userRepository.findById(id).map(UserDto::from);
    }

    @Override
    public Optional<UserDto> read(@NotNull String username) {
        return userRepository.findByUsername(username).map(UserDto::from);
    }

    @Override
    public Optional<UserDto> update(@NotNull UserDto userDto) {
        checkUsernameAvailability(userDto.getUsername());
        checkEmailAvailability(userDto.getEmail());

        setEncodedPassword(userDto);

        return userRepository.findById(userDto.getId()).map(userEntity ->
                UserDto.from(userRepository.save(userEntity.update(userDto)))
        );
    }

    @Override
    public boolean delete(@NotNull Long id) {
        return userRepository.findById(id).map(userEntity ->{
            userRepository.delete(userEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        // username과 password로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        try {
            // AuthenticationManager로 사용자 인증을 처리
            // - AuthenticationManager의 authenticate 메서드를 호출하면
            //   CustomUserDetailsService의 loadUserByUsername 메서드가 호출된다.
            Authentication authentication = authenticationManager.authenticate(token);

            // JwtProvider의 getJwtResponseDto 메서드로 Authentication의 정보로 JwtResponseDto를 생성해서 반환
            return jwtProvider.getJwtResponseDto(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    /**
     * username을 사용할 수 있는지 확인
     */
    private void checkUsernameAvailability(@NotNull String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already in use");
        }
    }

    /**
     * email을 사용할 수 있는지 확인
     */
    private void checkEmailAvailability(@NotNull String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already in use");
        }
    }

    /**
     * UserDto 인스턴스의 비밀번호를 암호화
     */
    public UserDto setEncodedPassword(@NotNull UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        return userDto;
    }
}