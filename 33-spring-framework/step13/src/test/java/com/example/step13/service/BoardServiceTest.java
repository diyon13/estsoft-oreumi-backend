package com.example.step13.service;

import com.example.step13.domain.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void testExists() {
        assertNotNull(boardService);

        log.info("boardService = {}", boardService);
    }

    @Test
    public void testCreate() {
        PostDto postDto = PostDto.builder()
                .title("[TEST] BoardServiceTest#testCreate")
                .content("[TEST] BoardServiceTest#testCreate")
                .writer("JUnit")
                .build();

        log.info("postDto = {}", postDto);

        boardService.create(postDto);
        log.info("postDto = {}", postDto);

        assertNotNull(postDto.getId());
    }

    @Test
    public void testRead() {
        Long id = 33L;

        PostDto postDto = boardService.read(id);

        assertNotNull(postDto);

        log.info("postDto = {}", postDto);
    }

    @Test
    public void testUpdate() {
        Long id = 100L;

        PostDto postDto = boardService.read(id);

        assertNotNull(postDto);

        postDto.setTitle("[TEST] BoardServiceTest#testUpdate");

        assertTrue(boardService.update(postDto));
    }

    @Test
    public void testDelete() {
        Long id = 93L;

//        assertTrue(boardService.delete(id));
        assertFalse(boardService.delete(id));
    }

    @Test
    public void testGetList() {
        List<PostDto> postDtoList = boardService.getList();

        assertNotNull(postDtoList);
        assertFalse(postDtoList.isEmpty());

        log.info("postDtoList.size() = {}", postDtoList.size());
    }

}