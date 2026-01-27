package com.example.step13.service;

import com.example.step13.domain.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentDto> create(CommentDto commentDto);
    Optional<CommentDto> read(Long id);
    Optional<CommentDto> update(CommentDto commentDto);
    boolean delete(Long id);
    List<CommentDto> getList(Long postId);
}