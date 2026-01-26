package com.example.step13.service;

import com.example.step13.domain.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    /**
     * 새로운 게시물을 등록
     *
     * @param postDto 등록할 게시물의 정보
     */
    void create(PostDto postDto);

    /**
     * 게시물의 조회
     *
     * @param id 조회할 게시물의 id
     * @return 조회한 게시물의 정보
     */
    PostDto read(Long id);

    /**
     * 게시물의 갱신
     *
     * @param postDto 갱신할 게시물의 정보
     * @return 게시물을 정상적으로 갱신했는지 여부
     */
    boolean update(PostDto postDto);

    /**
     * 게시물의 삭제
     *
     * @param id 삭제할 게시물의 id
     * @return 게시물을 정상적으로 삭제했는지 여부
     */
    boolean delete(Long id);

    /**
     * 게시물 목록을 조회
     *
     * @return PostDto 인스턴스들을 담고 있는 List 인스턴스
     */
    List<PostDto> getList();

    /**
     * 페이징 정보로 게시물 목록을 조회
     *
     * @param pageable 페이징 정보
     * @return PostDto 인스턴스들을 담고 있는 Page 인스턴스
     */
    Page<PostDto> getList(Pageable pageable);
}