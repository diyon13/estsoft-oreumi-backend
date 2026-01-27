package com.example.step13.service;

import com.example.step13.domain.CommentDto;
import com.example.step13.domain.CommentEntity;
import com.example.step13.domain.PostEntity;
import com.example.step13.persistence.CommentRepository;
import com.example.step13.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Optional<CommentDto> create(CommentDto commentDto) {
        log.info("create = {}", commentDto);

        Optional<CommentDto> result = Optional.empty();

        Optional<PostEntity> postEntityOptional = postRepository.findById(commentDto.getPostId());

        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            CommentEntity savedEntity = commentRepository.save(commentDto.toEntity(postEntity));

            result = Optional.of(CommentDto.from(savedEntity));
        }

        return result;
    }

    @Override
    public Optional<CommentDto> read(Long id) {
        log.info("read = {}", id);

        return commentRepository.findById(id).map(CommentDto::from);
    }

    @Override
    public Optional<CommentDto> update(CommentDto commentDto) {
        log.info("update = {}", commentDto);

        Optional<CommentDto> result = Optional.empty();

        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(commentDto.getId());

        if (commentEntityOptional.isPresent()) {
            CommentEntity commentEntity = commentEntityOptional.get()
                    .updateContent(commentDto.getContent())
                    .updateWriter(commentDto.getWriter());

            CommentEntity savedEntity = commentRepository.save(commentEntity);

            result = Optional.of(CommentDto.from(savedEntity));
        }

        return result;
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete = {}", id);

        return commentRepository.findById(id).map(commentEntity -> {
            commentRepository.delete(commentEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public List<CommentDto> getList(Long postId) {
        log.info("getList = {}", postId);

        return commentRepository.findByPostEntityId(postId).stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}