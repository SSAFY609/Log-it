package com.ssafy.logit.model.step_category.dto.category.etc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "기타 생성 요청")
public class CreateStepEtcRequest {

    @NotNull
    @Schema(description = "전형id")
    private Long stepId;
    @Max(3000)
    @Schema(description = "내용")
    private String content;

}
