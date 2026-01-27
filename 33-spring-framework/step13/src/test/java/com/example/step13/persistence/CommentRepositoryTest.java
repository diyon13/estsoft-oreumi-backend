package com.example.step13.persistence;

import com.example.step13.domain.CommentEntity;
import com.example.step13.domain.PostEntity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private final Long POST_ID = 1631L;

    @Test
    @DisplayName("CommentRepository 주입 테스트")
    public void testExists() {
        assertThat(commentRepository).isNotNull();

        log.info("commentRepository = {}", commentRepository);
    }

    @Test
    @Transactional
    public void testCreate() {
        final int LENGTH = 5;

        Optional<PostEntity> postEntityOptional = postRepository.findById(POST_ID);
        assertThat(postEntityOptional).isNotEmpty();

        PostEntity postEntity = postEntityOptional.get();

        for (int i = 1; i <= LENGTH; i++) {
            CommentEntity commentEntity = CommentEntity.builder()
                    .postEntity(postEntity)
                    .content("[TEST] CommentRepositoryTest#testCreate #" + i)
                    .writer("JUnit")
                    .build();

            CommentEntity savedEntity = commentRepository.save(commentEntity);
            log.info("savedEntity = {}", savedEntity);
        }

        List<CommentEntity> commentEntityList = postEntity.getCommentEntityList();
        assertThat(commentEntityList.size()).isEqualTo(LENGTH + 5);
    }

    @Test
    public void testRead() {
        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(3L);
        assertThat(commentEntityOptional).isNotEmpty();

        log.info("commentEntity = {}", commentEntityOptional.get());
    }

    @Test
    public void testUpdate() {
        final String content = "[TEST] CommentRepositoryTest#testUpdate";

        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(3L);
        assertThat(commentEntityOptional).isNotEmpty();

        CommentEntity commentEntity = commentEntityOptional.get()
                .updateContent(content);

        CommentEntity savedEntity = commentRepository.save(commentEntity);

        assertThat(savedEntity.getContent()).isEqualTo(content);
        log.info("savedEntity = {}", savedEntity);
    }

    @Test
    public void testDelete() {
        final long countBefore = commentRepository.count();

        Optional<CommentEntity> commentEntityOptional = commentRepository.findById(3L);
        assertThat(commentEntityOptional).isNotEmpty();

        CommentEntity commentEntity = commentEntityOptional.get();

        commentRepository.delete(commentEntity);

        final long countAfter = commentRepository.count();
        assertThat(countBefore - countAfter).isEqualTo(1);

        log.info("deletedEntity = {}", commentEntity);
        log.info("deleted records = {}", countBefore - countAfter);
    }

    @Test
    public void testGetList() {
        Optional<PostEntity> postEntityOptional = postRepository.findById(POST_ID);
        assertThat(postEntityOptional).isNotEmpty();

        PostEntity postEntity = postEntityOptional.get();

        List<CommentEntity> commentEntityList = commentRepository.findByPostEntity(postEntity);

        assertThat(commentEntityList).isNotEmpty();
        log.info("commentEntityList.size() = {}", commentEntityList.size());
    }

    @Test
    public void testGetList2() {
        List<CommentEntity> commentEntityList = commentRepository.findByPostEntityId(POST_ID);

        assertThat(commentEntityList).isNotEmpty();
        log.info("commentEntityList.size() = {}", commentEntityList.size());
    }
}