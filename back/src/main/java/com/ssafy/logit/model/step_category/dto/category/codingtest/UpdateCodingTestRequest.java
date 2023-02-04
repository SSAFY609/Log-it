package com.ssafy.logit.model.step_category.dto.category.codingtest;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCodingTestRequest {
    @NotNull
    private Long codingTestId;
    @Max(1000)
    private String algoContent;
    @Max(30)
    private String algoCategory;
}
