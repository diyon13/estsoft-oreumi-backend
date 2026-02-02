package com.example.step14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping({ "/board/", "/board/list" })
    public String board() {
        return "board/list";
    }

    @GetMapping({ "/admin/", "/admin/dashboard" })
    public String admin() {
        return "admin/dashboard";
    }
}