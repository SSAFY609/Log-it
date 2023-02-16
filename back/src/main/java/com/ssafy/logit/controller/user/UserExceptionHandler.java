package com.ssafy.logit.controller.user;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

    private final String EXPIRED = "token expired";

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> makeCookie(ExpiredJwtException e) {
        log.error("jwt token expired ", e);
        // return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<String>(EXPIRED, HttpStatus.UNAUTHORIZED);
    }
}
