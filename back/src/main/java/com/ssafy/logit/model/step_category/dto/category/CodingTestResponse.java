package com.ssafy.logit.model.step_category.dto.category;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import lombok.Data;

import javax.validation.constraints.Max;


@Data
public class CodingTestResponse {
    @Max(1000)
    private String content;
    @Max(30)
    private String category;

    // 생성자
    public CodingTestResponse(CodingTest codingTest) {
        this.content = codingTest.getContent();
        this.category = codingTest.getAlgoCategory();
    }

}
