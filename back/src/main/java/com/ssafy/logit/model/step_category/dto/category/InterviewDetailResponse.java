package com.ssafy.logit.model.step_category.dto.category;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import com.ssafy.logit.model.step_category.entity.category.InterviewDetail;
import lombok.Data;

@Data
public class InterviewDetailResponse {


    private Long id;
    private String question;
    private String content;
    private InterviewCategory interviewCategory;

    // 생성자
    public InterviewDetailResponse(InterviewDetail interviewDetail){
        this.id = interviewDetail.getId();
        this.question = interviewDetail.getQuestion();
        this.content = interviewDetail.getContent();
        this.interviewCategory = interviewDetail.getInterviewCategory();
    }


}
