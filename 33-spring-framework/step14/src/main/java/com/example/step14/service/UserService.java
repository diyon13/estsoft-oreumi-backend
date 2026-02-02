package com.example.step14.service;

import com.example.step14.domain.UserDto;

import java.util.Optional;

public interface UserService {
    void create(UserDto userDto);
    Optional<UserDto> read(Long id);
    Optional<UserDto> read(String username);
    Optional<UserDto> update(UserDto userDto);
    boolean delete(Long id);
}