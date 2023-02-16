package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.exception.WrongCategoryException;

public enum InterviewCategory {
    NETWORK("네트워크"),
    DATABASE("데이터베이스"),
    ALGORITHM("알고리즘"),
    OS("운영체제"),
    PROJECT("프로젝트"),
    CHARACTER("인성"),
    ETC("기타");

    private String value;

    InterviewCategory(String value) {
        this.value = value;
    }

    // String -> enum 변환
    public static InterviewCategory nameOf(String value){
        for(InterviewCategory category : InterviewCategory.values()){
            if(category.getValue().equals(value)){
                return category;
            }
        }
        return InterviewCategory.ETC; // default 기타
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
