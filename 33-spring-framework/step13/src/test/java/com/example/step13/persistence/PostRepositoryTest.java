package com.example.step13.persistence;

import com.example.step13.domain.PostEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testExists() {
        assertNotNull(postRepository);

        log.info("postRepository = {}", postRepository);
    }

    @Test
    public void testGetList() {
        List<PostEntity> postEntityList = postRepository.findAll();

        log.info("postEntityList.size() = {}", postEntityList.size());
    }

    @Test
    public void testCreate() {
        String title = "[Test] PostRepository testCreate";
        String writer = "JUnit";

        PostEntity postEntity = PostEntity.builder()
                .title(title)
                .content(title)
                .writer(writer)
                .build();

        PostEntity savedEntity = postRepository.save(postEntity);

        assertNotNull(savedEntity);
        assertEquals(title, savedEntity.getTitle());

        log.info("savedEntity = {}", savedEntity);
    }

    @Test
    public void tesRead() {
        Long id = 95L;

//        PostEntity postEntity = PostRepository.findById(id).get();

        postRepository.findById(id).ifPresentOrElse(
                postEntity -> log.info("postEntity = {}", postEntity),
                () -> { throw new RuntimeException(); });
    }

    @Test
    public void testUpdate() {
        Long id = 98L;
        String title = "[Test] PostRepository testUpdate";
        String writer = "JUnit";
        /*
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        assertTrue(optionalPostEntity.isPresent());

        PostEntity postEntity = optionalPostEntity.get();
         */

        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        log.info("postEntity = {}", postEntity);

        postEntity.updateTitle(title)
                .updateContent(title)
                .updateWriter(writer);

        PostEntity updatedEntity = postRepository.save(postEntity);

        assertNotNull(updatedEntity);
        assertEquals(title, updatedEntity.getTitle());
        log.info("updatedEntity = {}", updatedEntity);
    }
}