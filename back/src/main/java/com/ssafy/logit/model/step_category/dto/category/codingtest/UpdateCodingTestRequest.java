package com.ssafy.logit.model.step_category.dto.category.codingtest;

import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "코테 수정 요청")
public class UpdateCodingTestRequest {
    private Long codingTestId;
    @Max(1000)
    @Schema(description = "내용")
    private String algoContent;
    @Max(30)
    @Schema(description = "알고리즘 분류")
    private AlgoCategory algoCategory;
}
