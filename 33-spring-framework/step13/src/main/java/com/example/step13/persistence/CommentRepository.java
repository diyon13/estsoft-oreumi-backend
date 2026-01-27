package com.example.step13.persistence;

import com.example.step13.domain.CommentEntity;
import com.example.step13.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    /**
     * 특정 게시물의 댓글 목록을 조회
     *
     * @param postEntity 확인할 게시물
     * @return 댓글 목록
     */
    List<CommentEntity> findByPostEntity(PostEntity postEntity);

    /**
     * 특정 게시물의 댓글 목록을 조회
     *
     * @param postId 확인할 게시물의 ID
     * @return 댓글 목록
     */
    // List<CommentEntity> findByPostEntity_Id(Long postId);
    List<CommentEntity> findByPostEntityId(Long postId);
}