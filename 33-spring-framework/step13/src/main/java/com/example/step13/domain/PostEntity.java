package com.example.step13.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * posts 테이블과 매핑되는 JPA 엔티티(entity)
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "writer", length = 50, nullable = false)
    private String writer;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public PostEntity(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public PostEntity updateTitle(String title) {
        this.title = title;
        return this;
    }

    public PostEntity updateContent(String content) {
        this.content = content;
        return this;
    }

    public PostEntity updateWriter(String writer) {
        this.writer = writer;
        return this;
    }
}
