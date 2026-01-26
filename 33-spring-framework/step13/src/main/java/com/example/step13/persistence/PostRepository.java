package com.example.step13.persistence;

import com.example.step13.domain.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 엔티티에 매핑된 테이블을 대상으로 데이터베이스 연산을 수행할 리포지터리 인터페이스
 */
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}