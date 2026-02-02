package com.example.step14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
class ErrorController {
    @GetMapping("/403")
    public void handle403() {}
}