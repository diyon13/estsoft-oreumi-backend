package com.example.step13.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Long id;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

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

    /**
     * PostDto 인스턴스를 PostEntity 인스턴스로 변환
     *
     * @param postDto 변환할 PostDto 인스턴스
     * @return 변환한 PostEntity 인스턴스
     */
    public static PostEntity from(PostDto postDto) {
        if (postDto == null) {
            throw new IllegalArgumentException("postDto cannot be null");
        }

        return PostEntity.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .writer(postDto.getWriter())
                .build();
    }
}