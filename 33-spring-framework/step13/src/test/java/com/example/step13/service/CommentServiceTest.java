package com.example.step13.service;

import com.example.step13.domain.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    private final Long POST_ID = 1631L;

    @Test
    public void testExists() {
        assertThat(commentService).isNotNull();

        log.info("commentService = {}", commentService);
    }

    @Test
    public void testCreate() {
        CommentDto commentDto = CommentDto.builder()
                .postId(POST_ID)
                .content("[TEST] CommentServiceTest#testCreate()")
                .writer("JUnit")
                .build();

        Optional<CommentDto> commentDtoOptional = commentService.create(commentDto);

        assertThat(commentDtoOptional).isNotEmpty().hasValueSatisfying(savedDto -> {
            assertThat(savedDto).hasFieldOrPropertyWithValue("postId", POST_ID);
            log.info("savedDto = {}", savedDto);
        });
    }

    @Test
    public void testRead() {
        Optional<CommentDto> commentDtoOptional = commentService.read(4L);

        assertThat(commentDtoOptional).isNotEmpty().hasValueSatisfying(commentDto -> {
            assertThat(commentDto).hasFieldOrPropertyWithValue("postId", POST_ID);
            log.info("commentDto = {}", commentDto);
        });
    }

    @Test
    public void testUpdate() {
        Optional<CommentDto> commentDtoOptional = commentService.read(4L);
        assertThat(commentDtoOptional).isNotEmpty();

        CommentDto commentDto = commentDtoOptional.get();
        commentDto.setContent("[TEST] CommentServiceTest#testUpdate()");

        Optional<CommentDto> updatedDtoOptional = commentService.update(commentDto);
        assertThat(updatedDtoOptional).isNotEmpty();

        log.info("updatedDto = {}", updatedDtoOptional.get());
    }

    @Test
    public void testDelete() {
        assertThat(commentService.delete(2L)).isTrue();
    }

    @Test
    public void testGetList() {
        List<CommentDto> commentDtoList = commentService.getList(POST_ID);
        assertThat(commentDtoList).isNotEmpty();

        log.info("commentDtoList.size() = {}", commentDtoList.size());
    }
}