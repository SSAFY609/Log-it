package com.ssafy.logit.model.step_category.dto.category.etc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@Schema(description = "채용전형(기타) 수정 요청")
public class UpdateStepEtcRequest {
    @Schema(description = "전형(기타) id")
    private Long etcId;
    @Schema(description = "내용")
    @Max(3000)
    private String content;

}
