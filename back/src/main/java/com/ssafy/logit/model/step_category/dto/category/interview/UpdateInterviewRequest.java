package com.ssafy.logit.model.step_category.dto.category.interview;

import lombok.Data;

import javax.validation.constraints.Max;


@Data
public class UpdateInterviewRequest {

    @Max(1000)
    private String name;
}
