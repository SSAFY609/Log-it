package com.ssafy.logit.model.step_category.dto.category.interview;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Data
public class CreateInterviewRequest {

    @NotNull
    private Long stepId;
    @Max(1000)
    private String name;
}
