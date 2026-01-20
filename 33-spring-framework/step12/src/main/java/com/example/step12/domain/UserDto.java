package com.example.step12.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDto {
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
