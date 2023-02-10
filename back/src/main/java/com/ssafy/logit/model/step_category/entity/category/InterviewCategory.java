package com.ssafy.logit.model.step_category.entity.category;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
