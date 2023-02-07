package com.ssafy.logit.exHandler.advice;

import com.ssafy.logit.exHandler.ErrorResult;
import com.ssafy.logit.exception.WrongDateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = {"com.ssafy.logit.controller.job"})
public class JobControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> WrongDateHandler(WrongDateException e){
        log.error("[WrongDateException] Handler");
        ErrorResult errorResult = new ErrorResult("WrongDate",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }


}
