package com.ssafy.logit.model.step_category.dto.category;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class DocumentRequest {
    private Long stepId;
    @Max(200)
    private String question;
    @Max(1000)
    private String answer;

    private InterviewCategory interviewCategory;

}
