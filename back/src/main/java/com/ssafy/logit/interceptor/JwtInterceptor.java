package com.ssafy.logit.interceptor;

import com.ssafy.logit.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.StringTokenizer;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preflight를 위한 OPTIONS 요청은 그냥 전달
        
        log.info("JWT 인터셉터 호출");
        
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // request의 헤더에서 Authorization으로 넘어온 것을 찾음 (== jwt-auth-token)
        StringTokenizer st = new StringTokenizer(request.getHeader("Authorization"));
        String bearer = st.nextToken();
        String authToken = st.nextToken();
        log.debug("경로 : {}, 토큰 : {}", request.getServletPath(), authToken);

        // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생
        if(authToken != null) {
            System.out.println("@@@@@@@@@@@ authToken 확인 - jwtInterceptor @@@@@@@@@@@" + authToken);
            Map<String, Object> info = jwtUtil.checkAndGetClaims(authToken);
            log.info("하위!!!" + info);
            System.out.println("@@@@@@@@@@@ 여기까지 왔음 - jwtInterceptor @@@@@@@@@@@");
            Object info_email = info.get("user");
            String email = (String)info_email;
            System.out.println("?????? email ???????" + email);
            request.setAttribute("email", email);
            return true;
        } else {
            throw new RuntimeException("인증 토큰 없음");
        }
    }
}
