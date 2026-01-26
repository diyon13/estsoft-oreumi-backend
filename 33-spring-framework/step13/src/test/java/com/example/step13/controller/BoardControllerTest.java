package com.example.step13.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/list");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testListWithPaging() throws Exception {
        final String page = "1";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/list")
                .param("page", page);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testWrite() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/board/write")
                .param("title", "[TEST] BoardControllerTest#testWrite")
                .param("content", "[TEST] BoardControllerTest#testWrite")
                .param("writer", "JUnit");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testRead() throws Exception {
        final String id = "99";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/read")
                .param("id", id);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testReadWithPaging() throws Exception {
        final String id = "1620";
        final String page = "2";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/read")
                .param("id", id)
                .param("page", page);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        final String id = "96";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/board/update")
                .param("id", id)
                .param("title", "[TEST] BoardControllerTest#testUpdate")
                .param("content", "[TEST] BoardControllerTest#testUpdate")
                .param("writer", "JUnit");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        final String id = "91";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/delete")
                .param("id", id);

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }
}