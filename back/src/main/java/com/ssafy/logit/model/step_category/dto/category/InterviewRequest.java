package com.ssafy.logit.model.step_category.dto.category;

import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;


@Data
public class InterviewRequest {

    @Max(200)
    private String question;
    @Max(1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private InterviewCategory interviewCategory;

}
