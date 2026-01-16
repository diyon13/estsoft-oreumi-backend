package com.example.step11.ex01;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@RequiredArgsConstructor
class Hotel {
    private final Chef chef;
}
