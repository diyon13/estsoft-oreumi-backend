package com.example.step13.controller;

import com.example.step13.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/")
@Slf4j
@RequiredArgsConstructor
class BoardController {
    private final BoardService boardService;

    // "/board/"와 "/board/list"로 들어온 GET 방식의 요청에 매핑
//   @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
//    @GetMapping(value = {"", "/list"})
    @GetMapping({"", "/list"})
    public void list(Model model) {
        model.addAttribute("postDtoList", boardService.getList());
    }
}
