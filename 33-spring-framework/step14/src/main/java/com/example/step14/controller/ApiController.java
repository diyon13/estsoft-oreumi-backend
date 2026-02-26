package com.example.step14.controller;

import com.example.step14.domain.LoginDto;
import com.example.step14.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST API를 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
class ApiController {
    private final UserService userService;

    /**
     * REST API에서 응답하는 형식
     */
    private static record ApiResponse(
            int code,       // 20010
            String message,
            Object data
    ) {
        static ApiResponse of(int code, String message, Object data) {
            return new ApiResponse(code, message, data);
        }
    }

    @GetMapping({ "/admin/", "/admin/hello" })
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok().body(
                ApiResponse.of(HttpStatus.OK.value(), "success", "Welcome, Admin!")
        );
    }

    @GetMapping({ "/user/", "/user/hello" })
    public ResponseEntity<?> user() {
        return ResponseEntity.ok().body(
                ApiResponse.of(HttpStatus.OK.value(), "success", "Welcome, User!")
        );
    }

    /*
     * 비밀번호와 같이 민감한 개인 정보가 포함된 경우에는 반드시 HTTPS로 전달해야 한다.
     */

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().body(
                ApiResponse.of(HttpStatus.OK.value(), "success", userService.login(loginDto))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        // DTO의 제약 조건 주해에 설정한 예외 메시지를 저장할 HashMap 인스턴스
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(
                ApiResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage(), errors)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.badRequest().body(
                ApiResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null)
        );
    }
}