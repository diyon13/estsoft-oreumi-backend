package com.example.step12.controller;

import com.example.step12.domain.SampleDto;
import com.example.step12.domain.TodoDto;
import com.example.step12.domain.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/sample/")
@Slf4j
class SampleController {
    // "/sample/"로 들어온 요청에 매핑
//    @RequestMapping("")
    @RequestMapping
    public void index() {
        log.info("SampleController#index()");
    }

    // GET 방식으로 "sample/basic"으로 들어온 요청에 매핑
    // @RequestMapping("/basic")
    // @RequestMapping(value = "/basic", method = RequestMethod.GET)
    @GetMapping("/basic")
    public void basicGet() {
        log.info("SampleController#basicGet()");
    }

    @PostMapping("/basic")
    public void basicPost() {
        log.info("SampleController#basicPost()");
    }

    // "/sample/param"으로 들어온 요청에 매핑
    // "http://localhost:8080/sample/param?name=Harry&age=11"로 접속해서 확인
    @RequestMapping("/param")
//    public void param(@ModelAttribute SampleDto sampleDto) {
    public void param(SampleDto sampleDto) {
        log.info("SampleController#param()");
        log.info("sampleDto = {}", sampleDto);
    }

    // "/sample/param"으로 들어온 요청에 매핑
    // "http://localhost:8080/sample/param?name=Harry&age=11"로 접속해서 확인
    @RequestMapping("/param2")
//    public void param2(@RequestParam("name") String name,@RequestParam("age") int age) {
    public void param2(@RequestParam String name,@RequestParam int age) {
        log.info("SampleController#param2()");
        log.info("name = {}", name);
        log.info("age = {}", age);
    }

    // "yyyy-MM-dd" 형식의 요청 매개 변수를 Date 인스턴스로 변환
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        binder.registerCustomEditor(java.util.Date.class,
                new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/date")
    // "/sample/date"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/date?date=2026-11-25"로 접속해서 확인
    public void date(@RequestParam Date date) {
        log.info("SampleController#date()");
        log.info("date = {}", date);
    }

    // "/sample/todo"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/todo?title=Update+Spring+materials&dueDate=2026-11-25"로 접속해서 확인
    @RequestMapping("/todo")
    public void todo(TodoDto todoDto) {
        log.info("SampleController#todo()");
        log.info("todoDto = {}", todoDto);
    }
    // "/sample/user"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/user?username=harry&birthday=1980-07-31"로 접속해서 확인
    @RequestMapping("/user")
    public void user(UserDto userDto) {
        log.info("SampleController#user()");
        log.info("userDto = {}", userDto);
    }

    // "/sample/dateTime"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/dateTime?dateTime=2026-11-25+14:00:00"로 접속해서 확인
    @RequestMapping("/dateTime")
    public void dateTime(@RequestParam
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
        log.info("SampleController#dateTime()");
        log.info("datetime = {}", dateTime);
    }

    // "/sample/attr"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/attr?name=Harry&age=11&height=146"로 접속해서 확인
    @RequestMapping("/attr")
    public void attr(SampleDto sampleDto, @RequestParam int height) {
        log.info("SampleController#attr()");
        log.info("sampleDto = {}", sampleDto);
        log.info("height = {}", height);
    }

    // "/sample/attr2"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/attr2?name=Harry&age=11&height=146"로 접속해서 확인
    @RequestMapping("/attr2")
    public String attr2(SampleDto sampleDto, @RequestParam int height, Model model) {
        log.info("SampleController#attr2()");
        log.info("sampleDto = {}", sampleDto);
        log.info("height = {}", height);

        model.addAttribute("height", height);

        return "sample/attr";
    }

    // "/sample/response"로 들어온 요청에 매핑
    // "http://localhost:8080/sample/response"로 접속해서 확인
    @RequestMapping("/response")
    @ResponseBody
    public SampleDto response() {
        log.info("SampleController#response()");

        SampleDto sampleDto = new SampleDto();
        sampleDto.setName("Harry Potter");
        sampleDto.setAge(11);

        return sampleDto;
    }
}
