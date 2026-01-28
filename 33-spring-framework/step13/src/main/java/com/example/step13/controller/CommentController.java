package com.example.step13.controller;

import com.example.step13.domain.CommentDto;
import com.example.step13.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    // "/api/posts/{postId}/comments"로 들어온 POST 방식의 요청에 매핑
    @PostMapping
    public ResponseEntity<CommentDto> create(@PathVariable Long postId,
                                             @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.of(commentService.create(commentDto));
    }

    // "/api/posts/{postId}/comments/{commentId}"로 들어온 GET 방식의 요청에 매핑
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> read(@PathVariable Long postId, @PathVariable Long commentId) {
        return ResponseEntity.of(commentService.read(commentId));
    }

    // "/api/posts/{postId}/comments/{commentId}"로 들어온 PUT 방식의 요청에 매핑
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable Long postId,
                                             @PathVariable Long commentId,
                                             @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.of(commentService.update(commentDto));
    }

    // "/api/posts/{postId}/comments/{commentId}"로 들어온 DELETE 방식의 요청에 매핑
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.delete(commentId) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // "/api/posts/{postId}/comments"로 들어온 GET 방식의 요청에 매핑
    @GetMapping
    public ResponseEntity<List<CommentDto>> readAll(@PathVariable Long postId) {
        return ResponseEntity.ofNullable(commentService.getList(postId));
    }

    // Validation API에서 발생하는 MethodArgumentNotValidException 예외를 처리할 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}