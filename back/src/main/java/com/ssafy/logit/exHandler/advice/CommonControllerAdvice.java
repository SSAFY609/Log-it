package com.ssafy.logit.exHandler.advice;

import com.ssafy.logit.exHandler.ErrorResult;
import com.ssafy.logit.exception.AuthorizationException;
import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResult> differentExHandler(DifferentUserException e) {
        log.error("[DifferentUserException] Handler");
        ErrorResult errorResult = new ErrorResult("userDiffernet", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> authorizationExhandler(AuthorizationException e) {
        log.error("[AuthorizationException] Handler");
        ErrorResult errorResult = new ErrorResult("권한 없음. 토큰 확인", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> wrongCategoryExHandler(WrongCategoryException e) {
        log.error("[WrongCategoryException] Handler");
        ErrorResult errorResult = new ErrorResult("카테고리 확인", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);

    }

}
