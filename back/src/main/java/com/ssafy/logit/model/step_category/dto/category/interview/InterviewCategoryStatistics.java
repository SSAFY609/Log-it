package com.ssafy.logit.model.step_category.dto.category.interview;

import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

@Data
public class InterviewCategoryStatistics {
    private String interviewCategory;
    private Long cnt;

    public InterviewCategoryStatistics(InterviewCategory interviewCategory,Long cnt){
        this.interviewCategory = interviewCategory.getValue();
        this.cnt = cnt;
    }

}
