package com.ssafy.logit.model.step_category.entity.category;

public enum AlgoCategory {
    BFS("BFS"),
    DFS("DFS"),
    GRAPH("그래프 구현"),
    SIMULATION("구현"),
    STRING("문자열"),
    SORT("정렬"),
    BINARYSEARCH("이분탐색"),
    DATASTRUCTURE("자료 구조"),
    BRUTEFORCE("완전탐색"),
    HEAP("힙"),
    TREE("트리"),
    GRAPHSEARCH("그래프 탐색"),
    DP("동적 프로그래밍"),
    GREEDY("그리디"),
    BACKTRACKING("백 트래킹");


    private String value;

    AlgoCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
