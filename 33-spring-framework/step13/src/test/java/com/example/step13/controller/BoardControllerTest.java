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

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/board/list");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());

    }
}