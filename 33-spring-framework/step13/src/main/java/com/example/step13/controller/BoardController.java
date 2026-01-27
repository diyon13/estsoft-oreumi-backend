package com.example.step13.controller;

import com.example.step13.domain.Criteria;
import com.example.step13.domain.Pagination;
import com.example.step13.domain.PostDto;
import com.example.step13.domain.ResultDto;
import com.example.step13.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@Slf4j
@RequiredArgsConstructor
class BoardController {
    private final BoardService boardService;

    /*
    // "/board/"와 "/board/list"로 들어온 GET 방식의 요청에 매핑
    // @RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
    // @GetMapping(value = { "", "/list" })
    @GetMapping({ "", "/list" })
    public void list(Model model) {
        model.addAttribute("postDtoList", boardService.getList());
    }
    */

    // "/board/"와 "/board/list"로 들어온 GET 방식의 요청에 매핑
    @GetMapping({ "", "/list" })
    public String list(Criteria criteria, Model model) {
        log.info("list = {}", criteria);

        Pageable pageable = PageRequest.of(criteria.getPage() - 1,
                criteria.getSize(), Sort.by(Sort.Direction.DESC, "id"));

        log.info("pageable = {}", pageable);

        Page<PostDto> postDtoPage = boardService.getList(pageable);

        Pagination pagination = Pagination.of(pageable,
                postDtoPage.getTotalElements(), postDtoPage.getTotalPages());

        log.info("pagination = {}", pagination);

        model.addAttribute("postDtoList", postDtoPage);
        model.addAttribute("pagination", pagination);

        return "board/list";
    }

    // "/board/write"로 들어온 POST 방식의 요청에 매핑
    // @RequestMapping(value = "/write", method = RequestMethod.POST)
    @PostMapping("/write")
    public String write(PostDto postDto, RedirectAttributes redirectAttributes) {
        log.info("write = {}", postDto);

        // TODO: 요청 매개 변수를 검증

        boardService.create(postDto);

        // RedirectAttributes 인스턴스에 URL 재지정할 때 전달할 값을 추가
        redirectAttributes.addAttribute("id", postDto.getId());
        // → /board/read?id=200

        // redirectAttributes.addAttribute("result", "success");
        // → /board/read?id=200&result=success

        // redirectAttributes.addFlashAttribute("result", "success");
        // redirectAttributes.addFlashAttribute("action", "write");

        redirectAttributes.addFlashAttribute("result", ResultDto.of(true, "write"));

        // URL 재지정(redirect)
        return "redirect:/board/read";
    }

    // "/board/write"로 들어온 GET 방식 요청에 매핑
    @GetMapping("/write")
    public void write(Model model) {
        model.addAttribute("action", "write");
        model.addAttribute("title", "Write a new post");
    }

    /*
    // "/board/read"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/read")
    public void read(@RequestParam Long id, Model model) {
        log.info("read = {}", id);

        model.addAttribute("postDto", boardService.read(id));
    }
    */

    // "/board/read"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/read")
    public PostDto read(@RequestParam Long id, Criteria criteria) {
        log.info("read = {}", id);
        log.info("criteria = {}", criteria);

        return boardService.read(id);
    }

    // "/board/update"로 들어온 POST 방식 요청에 매핑
    @PostMapping("/update")
    public String update(PostDto postDto, Criteria criteria, RedirectAttributes redirectAttributes) {
        log.info("update = {}", postDto);
        log.info("criteria = {}", criteria);

        if (boardService.update(postDto)) {
            redirectAttributes.addFlashAttribute("result", ResultDto.of(true, "update"));
        }

        redirectAttributes.addAttribute("id", postDto.getId());
        redirectAttributes.addAttribute("page", criteria.getPage());

        return "redirect:/board/read";
    }

    // "/board/update"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/update")
    public String update(@RequestParam Long id, Criteria criteria, Model model) {
        log.info("update = {}", id);
        log.info("criteria = {}", criteria);

        model.addAttribute("postDto", boardService.read(id));
        model.addAttribute("action", "update");
        model.addAttribute("title", "Update the Post");

        return "board/write";
    }

    // "/board/delete"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Criteria criteria, RedirectAttributes redirectAttributes) {
        log.info("delete = {}", id);
        log.info("criteria = {}", criteria);

        if (boardService.delete(id)) {
            redirectAttributes.addFlashAttribute("result", ResultDto.of(true, "delete"));
        }

        redirectAttributes.addAttribute("page", criteria.getPage());

        return "redirect:/board/list";
    }
}