package com.ssafy.logit.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWithIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class JwtTest {

    @Autowired
    JwtUtil util;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void tokenGetTest() {
        // given
        String email = "logit@logit.com";

        // when
        String token = util.createAuthToken(email);

        // then
        assertNotNull(token);
        log.debug(token);
    }

    @Test
    public void contentTest() {
        // given
        String email = "logit@logit.com";

        // when
        String token = util.createAuthToken(email);
        Map<String, Object> content = util.checkAndGetClaims(token);

        // then
        assertEquals(content.get("sub"), "authToken");
        assertEquals(content.get("user"), email);
    }

    @Test
    public void wrongTokenTest() {
        // given
        String fakeToken = "fakeToken";

        // when-then
        assertThrows(MalformedJwtException.class, ()-> {
            util.checkAndGetClaims(fakeToken);
        });
    }

    @Test
    public void expireTest() {
        // given
        String fakeToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdXRoVG9rZW4iLCJleHAiOjE2NzQ1NDkzNDgsInVzZXIiOiJzc2FmeUBzc2FmeS5jb20ifQ.oMbI5Z1CGuzrwBEkDT0ZlYeJ6XALRFzKLetX4xbR6tg";

        //when-then
        assertThrows(ExpiredJwtException.class, ()->{
            util.checkAndGetClaims(fakeToken);
        });
    }

    @Test
    public void testLogin() throws Exception {
        // given
        Map<String, String> map = new HashMap<>();
        map.put("email", "seolhee2750@naver.com");
        map.put("pw", "1234");
        String content = new ObjectMapper().writeValueAsString(map);

        //when
        MockHttpServletRequestBuilder requestBuilder = post("/user/login").contentType("application/json").content(content);
        ResultActions action = mockMvc.perform(requestBuilder);

        //then
        action.andExpect(status().is(202)).andExpect(jsonPath("$.sub", equalTo("authToken"))).andExpect(jsonPath("$.user", equalTo(map.get("email"))));
    }

    @Test
    public void testGetInfoSuccess() throws Exception{
        // given
        String token = util.createAuthToken("seolhee2750@naver.com");

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/user/get").header("jwt-auth-token", token);
        ResultActions action = mockMvc.perform(requestBuilder);

        // then
        action.andExpect(status().is(200));
    }

    @Test
    public void testGetInfoFail() throws Exception {
        // given
        String token = "malfomed token";

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/user/get").header("jwt-auth-token", token);

        // then
        assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder);
        });
    }
}
