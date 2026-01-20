package com.example.step12.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
class CommonExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        log.error("Exception: {}",e.getMessage());

        model.addAttribute("exception",e);

        // 뷰 이름을 별도로 지정
        return "error-page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e, Model model) {
        log.error("Exception: {}", e.getMessage());

        model.addAttribute("exception",e);

        return "error404";
    }
}
