package com.ssafy.logit.model.step_category.dto.category.entire;

import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

@Data
public class AllCategoryRequest {
    private Long id;
    private String question;
    private String answer;
    private String content;
    private AlgoCategory algoCategory;
    private InterviewCategory interviewCategory;
}
