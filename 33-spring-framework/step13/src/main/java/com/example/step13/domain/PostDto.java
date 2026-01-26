package com.example.step13.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 게시물 정보를 계층 간에 전달하기 위한 DTO(Data Transfer Object)
 */
@Data
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * PostEntity 인스턴스를 PostDto 인스턴스로 변환
     *
     * @param postEntity 변환할 PostEntity 인스턴스
     * @return 변환한 PostDto 인스턴스
     */
    public static PostDto from(PostEntity postEntity) {
        if (postEntity == null) {
            throw new IllegalArgumentException("postEntity cannot be null");
        }

        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .build();
    }
}
