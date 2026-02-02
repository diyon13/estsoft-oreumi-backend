package com.example.step14.controller;

import com.example.step14.domain.UserDto;
import com.example.step14.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public String signup(@Valid UserDto userDto) {
        userService.create(userDto);

        return "redirect:/login";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "redirect:/signup";
    }
}