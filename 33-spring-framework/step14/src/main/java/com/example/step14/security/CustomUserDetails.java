package com.example.step14.security;

import com.example.step14.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NullMarked
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserDto user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 정적 팩토리 메서드(static factory method)
    public static UserDetails of(UserDto user) {
        return new CustomUserDetails(user);
    }
}