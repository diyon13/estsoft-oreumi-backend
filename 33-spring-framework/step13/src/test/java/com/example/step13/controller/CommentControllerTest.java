package com.example.step13.controller;

import com.example.step13.domain.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Long POST_ID = 1631L;
    private final String URI = String.format("/api/posts/%d/comments", POST_ID);

    @Test
    public void testCreate() throws Exception {
        CommentDto commentDto = CommentDto.builder()
                .postId(POST_ID)
                .content("[TEST] CommentControllerTest#testCreate")
                .writer("JUnit")
                .build();

        String json = objectMapper.writeValueAsString(commentDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(POST_ID))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print())
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());

        // Long postId = jsonObject.getLong("postId");
        // String writer = jsonObject.getString("writer");

        // 잘못된 방법!
        // assertThat(jsonObject)
        //     .hasFieldOrPropertyWithValue("postId", POST_ID)
        //     .hasFieldOrPropertyWithValue("writer", "JUnit");

        assertThat(jsonObject.getLong("postId")).isEqualTo(POST_ID);
        assertThat(jsonObject.getString("writer")).isEqualTo("JUnit");

        assertThat(jsonObject);
    }

    @Test
    public void testCreateFail() throws Exception {
        CommentDto commentDto = CommentDto.builder()
                .postId(POST_ID)
                // .content("[TEST] CommentControllerTest#testCreate")
                .writer("JUnit")
                .build();

        String json = objectMapper.writeValueAsString(commentDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void testRead() throws Exception {
        final long commentId = 25L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URI + "/" + commentId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(POST_ID))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        final long commentId = 25L;
        final String writer = "JUnit TEST";

        CommentDto commentDto = CommentDto.builder()
                .id(commentId)
                .postId(POST_ID)
                .content("[TEST] CommentControllerTest#testUpdate")
                .writer(writer)
                .build();

        String json = objectMapper.writeValueAsString(commentDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(URI + "/" + commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(POST_ID))
                .andExpect(jsonPath("$.writer").value(writer))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        final long commentId = 25L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(URI + "/" + commentId);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetList() throws Exception {
        /*
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(URI);

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].postId").value(POST_ID))
            .andDo(print());
        */

        performRequest("GET", URI, null, null)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].postId").value(POST_ID))
                .andDo(print());
    }

    private ResultActions performRequest(String method, String uri, MediaType contentType, String content)
            throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.request(HttpMethod.valueOf(method), uri);

        if (contentType != null) {
            request.contentType(contentType);
        }

        if (contentType != null) {
            request.content(content);
        }

        return mockMvc.perform(request);
    }

    private ResultActions performRequest(String method, String uri, MediaType contentType) throws Exception {
        return performRequest(method, uri, contentType, null);
    }

    private ResultActions performRequest(String method, String uri) throws Exception {
        return performRequest(method, uri, null, null);
    }
}