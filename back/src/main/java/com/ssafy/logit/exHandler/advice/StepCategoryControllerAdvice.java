package com.ssafy.logit.exHandler.advice;


import com.ssafy.logit.exHandler.ErrorResult;
import com.ssafy.logit.exception.WrongCategoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(basePackages = {"com.ssafy.logit.controller.step_category"})
public class StepCategoryControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> NoSuchElementHandler(NoSuchElementException e){
        log.error("[NoSuchElementException] Handler");
        ErrorResult errorResult = new ErrorResult("NoSuchElement",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> NoSuchElementHandler(WrongCategoryException e){
        log.error("[WrongCategoryException] Handler");
        ErrorResult errorResult = new ErrorResult("WrongCategory",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }



}
