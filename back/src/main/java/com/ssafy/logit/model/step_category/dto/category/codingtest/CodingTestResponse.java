package com.ssafy.logit.model.step_category.dto.category.codingtest;

import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "코테 응답")
public class CodingTestResponse {

    @Schema(description = "코테 id")
    private Long id;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "알고리즘 분류" )
    private AlgoCategory category;

    // 생성자
    public CodingTestResponse(CodingTest codingTest) {
        this.id = codingTest.getId();
        this.content = codingTest.getContent();
        this.category = codingTest.getAlgoCategory();
    }

}
