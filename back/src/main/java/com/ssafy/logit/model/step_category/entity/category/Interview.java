package com.ssafy.logit.model.step_category.entity.category;


import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
public class Interview {
    @Id
    @GeneratedValue
    @Column(name = "interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;


    @Max(200)
    private String question;
    @Max(1000)
    private String answer;

    @Enumerated(EnumType.STRING)
    private InterviewCategory interviewCategory;



    // private setter //
    private void addStepCategory(StepCategory stepCategory) {
        if (stepCategory == null) {
            throw new IllegalStateException();
        }
        stepCategory.getInterviewList().add(this);
        this.stepCategory = stepCategory;
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
    public static Interview create(StepCategory stepCategory, String question, String answer, InterviewCategory interviewCategory) {
        Interview interview = new Interview();
        interview.addStepCategory(stepCategory);
        interview.setQuestion(question);
        interview.setAnwer(answer);
        interview.setInterviewCategory(interviewCategory);
        return interview;
    }

    // 수정 메소드 //
    public Interview update(String question, String answer, InterviewCategory interviewCategory) {
        this.setQuestion(question);
        this.setAnwer(answer);
        this.setInterviewCategory(interviewCategory);
        return this;
    }

}
