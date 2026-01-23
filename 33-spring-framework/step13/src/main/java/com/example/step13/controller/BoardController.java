package com.example.step13.controller;

import com.example.step13.domain.PostDto;
import com.example.step13.domain.ResultDto;
import com.example.step13.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String list(Model model) {
        model.addAttribute("postDtoList", boardService.getList());

        return "board/list";
    }

    // "/board/writer"로 들어온 POST 방식의 요청에 매핑
//    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @PostMapping("/write")
    public String write(PostDto postDto, RedirectAttributes redirectAttributes) {
        log.info("write = {}", postDto);

        // TODO: 요청 매개 변수를 검증

        boardService.create(postDto);

        // RedirectAttributes 인스턴스에 URL 재지정할 때 전달할 값을 추가
        redirectAttributes.addAttribute("id", postDto.getId());
        // /board/read?id=200

        // redirectAttributes.addAttribute("result, "success")
        // /board/read?id=200&result=success

//        redirectAttributes.addFlashAttribute("result", "success");
//        redirectAttributes.addFlashAttribute("action", "write");

        redirectAttributes.addFlashAttribute("result", ResultDto.of(true, "write"));

        //URL 재지정(redirect)
        return "redirect:/board/read";
    }

    // "/board/update"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
        log.info("update = {}", id);

        model.addAttribute("postDto", boardService.read(id));
        model.addAttribute("action", "update");
        model.addAttribute("title", "Update the post");

        return "board/write";
    }

    // "/board/write"로 들어온 GET 방식 요청에 매핑
    @GetMapping("/write")
    public void write(Model model) {
        model.addAttribute("action", "write");
        model.addAttribute("title", "Write a new post");
    }

    // "/board/read"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/read")
    public PostDto read(@RequestParam Long id) {
        log.info("read = {}", id);

        return boardService.read(id);
    }

    // "/board/update"로 들어온 POST 방식 요청에 매핑
    @PostMapping("/update")
    public String update(PostDto postDto, RedirectAttributes redirectAttributes) {
        log.info("update = {}", postDto);

        if (boardService.update(postDto)) {
            redirectAttributes.addAttribute("result", ResultDto.of(true, "update"));
        }

        redirectAttributes.addAttribute("id", postDto.getId());

        return "redirect:/board/read";
    }

    // "/board/delete"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        log.info("delete = {}", id);

    if (boardService.delete(id)) {
        redirectAttributes.addFlashAttribute("result", ResultDto.of(true, "delete"));
    }

        return "redirect:/board/list";
    }
}
