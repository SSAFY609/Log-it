package com.ssafy.logit.model.step_category.dto.category;

import lombok.Data;

import javax.validation.constraints.Max;


@Data
public class CodingTestRequest {
    private Long stepId;
    @Max(1000)
    private String algoContent;
    @Max(30)
    private String category;

}
