package com.example.step13.service;

import com.example.step13.domain.PostDto;
import com.example.step13.domain.PostEntity;
import com.example.step13.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final PostRepository postRepository;

    @Override
    public void create(PostDto postDto) {
        log.info("CREATE: postDto = {}", postDto);

        /*
        PostEntity postEntity = PostEntity.builder()
            .title(postDto.getTitle())
            .content(postDto.getContent())
            .writer(postDto.getWriter())
            .build();

        PostEntity savedEntity = postRepository.save(postEntity);
        */

        PostEntity savedEntity = postRepository.save(PostEntity.from(postDto));
        log.info("CREATE: savedEntity = {}", savedEntity);

        postDto.setId(savedEntity.getId());
    }

    @Override
    public PostDto read(Long id) {
        log.info("READ: id = {}", id);

        /*
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();

            return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .build();
        }

        return null;
        */

        // postRepository.findById(id).map(postEntity -> { return PostDto.from(postEntity); }).orElse(null);
        // postRepository.findById(id).map(postEntity -> PostDto.from(postEntity)).orElse(null);
        return postRepository.findById(id).map(PostDto::from).orElse(null);
    }

    @Override
    public boolean update(PostDto postDto) {
        log.info("UPDATE: postDto = {}", postDto);

        return postRepository.findById(postDto.getId()).map(postEntity -> {
            postEntity.updateTitle(postDto.getTitle())
                    .updateContent(postDto.getContent())
                    .updateWriter(postDto.getWriter());

            postRepository.save(postEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean delete(Long id) {
        log.info("DELETE: id = {}", id);

        return postRepository.findById(id).map(postEntity -> {
            postRepository.delete(postEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public List<PostDto> getList() {
        List<PostDto> postDtoList = new ArrayList<>();

        List<PostEntity> postEntityList = postRepository.findAll();

        /*
        for (PostEntity postEntity : postEntityList) {
            postDtoList.add(PostDto.from(postEntity));
        }
        */

        // postEntityList.forEach(postEntity -> { postDtoList.add(PostDto.from(postEntity)); });
        postEntityList.forEach(postEntity -> postDtoList.add(PostDto.from(postEntity)));

        return postDtoList;
    }

    @Override
    public Page<PostDto> getList(Pageable pageable) {
        log.info("getList: pageable = {}", pageable);

        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);

        // return postEntityPage.map(postEntity -> { return PostDto.from(postEntity); });
        // return postEntityPage.map(postEntity -> PostDto.from(postEntity));
        return postEntityPage.map(PostDto::from);
    }
}