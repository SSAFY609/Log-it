package com.ssafy.logit.model.common;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public class ResultDto<T>{
    // 개수와 data을 반환해주는 dto클래스
    private int count;
    private T data;
}