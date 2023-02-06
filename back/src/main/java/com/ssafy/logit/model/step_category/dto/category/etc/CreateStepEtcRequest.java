package com.ssafy.logit.model.step_category.dto.category.etc;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class CreateStepEtcRequest {

    @NotNull
    private Long stepId;
    @Max(3000)
    private String content;

}
