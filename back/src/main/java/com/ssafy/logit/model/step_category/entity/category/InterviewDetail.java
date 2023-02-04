package com.ssafy.logit.model.step_category.entity.category;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
public class InterviewDetail {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id")
    private Interview interview;


    @Max(200)
    private String question;
    @Max(1000)
    private String answer;

    @Enumerated(EnumType.STRING)
    private InterviewCategory interviewCategory;


    // private setter //
    private void setInterview(Interview interview) {
        if (interview == null) {
            throw new IllegalStateException();
        }
        this.interview = interview;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    private void setAnwer(String answer) {
        this.answer = answer;
    }

    private void setInterviewCategory(InterviewCategory interviewCategory) {
        this.interviewCategory = interviewCategory;
    }


    // 생성 메소드 //
    public static InterviewDetail create(Interview interview, String question, String answer, InterviewCategory interviewCategory) {
        InterviewDetail interviewDetail = new InterviewDetail();
        interviewDetail.setInterview(interview);
        interviewDetail.setQuestion(question);
        interviewDetail.setAnwer(answer);
        interviewDetail.setInterviewCategory(interviewCategory);

        return interviewDetail;
    }

    // 수정 메소드 //
    public InterviewDetail update(String question, String answer, InterviewCategory interviewCategory) {
        this.setQuestion(question);
        this.setAnwer(answer);
        this.setInterviewCategory(interviewCategory);
        return this;
    }

}
