package com.ssafy.logit.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    public String createAuthToken(String email) {
        return create(email, "authToken", expireMin);
    }

    public String createRefreshToken() {
        // 인증 정보는 유지하지 않고, 유효 기간을 auth-token의 5배로 설정
        return create(null, "refreshToken", expireMin * 15);
    }

    // 로그인 성공 시 사용자 정보를 기반으로 jwt 토큰을 생성해서 반환
    private String create(String email, String subject, long expireMin) {
        final JwtBuilder builder = Jwts.builder();

        // Header 설정
        builder.setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin));

        // 담고 싶은 정보 설정
        if(email != null) {
            builder.claim("user", email);
        }

        // signature - secret key를 이용한 암호화
        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

        // 마지막 직렬화 처리
        final String jwt = builder.compact();
        log.debug("토큰 발행 : {}", jwt);
        return jwt;
    }

    // jwt 토큰을 분석해서 필요한 정보 반환
    public Map<String, Object> checkAndGetClaims(final String jwt) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        log.trace("claims : {}", claims);
        log.trace("salt : " + salt + " expireMin" + expireMin);
        log.trace("claims.getBody() : " + claims.getBody());
        return claims.getBody();
    }
}
