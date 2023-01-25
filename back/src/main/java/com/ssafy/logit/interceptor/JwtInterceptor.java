package com.ssafy.logit.interceptor;

import com.ssafy.logit.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preflight를 위한 OPTIONS 요청은 그냥 전달
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // request의 헤더에서 jwt-auth-token으로 넘어온 것을 찾음
        String authToken = request.getHeader("jwt-auth-token");
        log.debug("경로 : {}, 토큰 : {}", request.getServletPath(), authToken);

        // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생
        if(authToken != null) {
            jwtUtil.checkAndGetClaims(authToken);
            return true;
        } else {
            throw new RuntimeException("인증 토큰 없음");
        }
    }
}
