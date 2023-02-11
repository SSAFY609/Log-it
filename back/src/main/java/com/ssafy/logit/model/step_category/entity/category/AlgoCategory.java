package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.exception.WrongCategoryException;

public enum AlgoCategory {
    BFS("BFS"),DFS("DFS"),GRAPH("그래프 구현"),SIMULATION("구현"),STRING("문자열"),SORT("정렬"),
    BINARYSEARCH("이분탐색"),DATASTRUCTURE("자료 구조"),BRUTEFORCE("완전탐색"),HEAP("힙"),GRAPHSEARCH("그래프 탐색"),
    DP("동적 프로그래밍");


    private String value;
    AlgoCategory(String value) {
        this.value = value;
    }
    
    // name -> enum 타입 변환
    public static AlgoCategory nameOf(String value){
        for(AlgoCategory category : AlgoCategory.values()){
            if(category.getValue().equals(value)){
                return category;
            }
        }
        throw new WrongCategoryException("알고리즘 카테고리가 없습니다.");
    }

    public String getValue() {
        return value;
    }
}
