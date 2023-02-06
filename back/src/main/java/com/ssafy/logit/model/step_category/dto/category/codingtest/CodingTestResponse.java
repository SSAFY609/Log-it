package com.ssafy.logit.model.step_category.dto.category.codingtest;

import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import lombok.Data;


@Data
public class CodingTestResponse {
    private Long id;
    private String content;
    private String category;

    // 생성자
    public CodingTestResponse(CodingTest codingTest) {
        this.id = codingTest.getId();
        this.content = codingTest.getContent();
        this.category = codingTest.getAlgoCategory();
    }

}
