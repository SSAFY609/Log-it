package com.ssafy.logit.model.step_category.dto.category.etc;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class UpdateStepEtcRequest {

    @Max(3000)
    private String content;

}
