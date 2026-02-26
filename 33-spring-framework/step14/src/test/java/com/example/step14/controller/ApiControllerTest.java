package com.example.step14.controller;

import com.example.step14.domain.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String username = "potter";
    private final String password = "deer";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginFail() throws Exception {
        // 실패하는 로그인 수행
        performLogin(username, "door")
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void testLoginSuccess() throws Exception {
        // 성공하는 로그인 수행
        String responseBody = performLogin(username, password)
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        // 응답 바디로 JSONObject를 생성
        JSONObject jsonObject = new JSONObject(responseBody);
        jsonObject = (JSONObject) jsonObject.get("data");

        log.info("grantType: {}", jsonObject.get("grantType"));
        log.info("accessToken: {}", jsonObject.get("accessToken"));
        log.info("refreshToken: {}", jsonObject.get("refreshToken"));
    }

    @Test
    void testAccessUserPageFail() throws Exception {
        // 실패하는 요청 수행
        performRequest("/api/user/hello", null)
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void testAccessUserPageSuccess() throws Exception {
        /*
        // 로그인 수행
        String responseBody = performLogin(username, password)
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        // 응답 바디에서 Access 토큰을 추출
        JSONObject jsonObject = new JSONObject(responseBody);
        jsonObject = (JSONObject) jsonObject.get("data");

        String grantType = jsonObject.getString("grantType");
        String accessToken = jsonObject.getString("accessToken");
        String refreshToken = jsonObject.getString("refreshToken");

        // 성공하는 요청 수행
        performRequest("/api/user/hello", accessToken)
            .andExpect(status().isOk())
            .andDo(print());
        */

        // 성공하는 요청 수행
        performRequest("/api/user/hello", getAccessToken())
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testAccessAdminPageFail() throws Exception {

        /*
         * 실제 서비스에서는 Access 토큰을 발급받으면 만료될 때까지 계속 사용한다.
         *
         * 각 테스트는 독립적으로 수행되어야 한다.
         * 그래서 Access 토큰을 변수에 대입해서 사용하지 않고 매 테스트마다 Access 토큰을 발급받는다.
         */

        // 실패하는 요청 수행
        performRequest("/api/admin/hello", getAccessToken())
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    /**
     * LoginDto를 생성해서 로그인을 수행
     */
    private ResultActions performLogin(String username, String password) throws Exception {
        // LoginDto 생성
        LoginDto loginDto = LoginDto.builder()
                .username(username)
                .password(password)
                .build();

        // loginDto 객체를 직렬화해서 JSON 문자열을 생성
        String json = objectMapper.writeValueAsString(loginDto);

        // POST 방식의 요청을 생성
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // MockMvc 인스턴스의 perform 메서드로 요청을 수행하고, ResultActions를 반환
        return mockMvc.perform(request);
    }

    /**
     * GET 방식 요청을 생성해서 수행
     */
    private ResultActions performRequest(String url, String accessToken) throws Exception {
        // GET 방식의 요청을 생성
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url);

        // Access 토큰 설정
        if (accessToken != null) {
            request.header("Authorization", "Bearer " + accessToken);
        }

        // MockMvc 인스턴스의 perform 메서드로 요청을 수행하고, ResultActions를 반환
        return mockMvc.perform(request);
    }

    /**
     * 로그인을 수행하고 Access 토큰을 반환
     */
    private String getAccessToken() throws Exception {
        // 로그인 수행
        String responseBody = performLogin(username, password)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // 응답 바디에서 Access 토큰을 추출
        JSONObject jsonObject = new JSONObject(responseBody);
        jsonObject = (JSONObject) jsonObject.get("data");

        return jsonObject.getString("accessToken");
    }
}