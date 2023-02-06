package com.ssafy.logit.model.step_category.dto.category.interview;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import lombok.Data;

@Data
public class InterviewResponse {


    private Long id;
    private String question;
    private String answer;
    private InterviewCategory interviewCategory;

    // 생성자
    public InterviewResponse(Interview interviewDetail){
        this.id = interviewDetail.getId();
        this.question = interviewDetail.getQuestion();
        this.answer = interviewDetail.getAnswer();
        this.interviewCategory = interviewDetail.getInterviewCategory();
    }


}
