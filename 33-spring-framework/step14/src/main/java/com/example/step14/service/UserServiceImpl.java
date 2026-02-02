package com.example.step14.service;

import com.example.step14.domain.UserDto;
import com.example.step14.domain.UserEntity;
import com.example.step14.persistence.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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