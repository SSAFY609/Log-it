package com.ssafy.logit.model.step_category.dto.category.interview;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import lombok.Data;

@Data
public class InterviewResponse {


    private Long id;
    private String question;
    private String answer;
    private InterviewCategory category;

    // 생성자
    public InterviewResponse(Interview interview){
        this.id = interview.getId();
        this.question = interview.getQuestion();
        this.answer = interview.getAnswer();
        this.category = interview.getInterviewCategory();
    }


}
