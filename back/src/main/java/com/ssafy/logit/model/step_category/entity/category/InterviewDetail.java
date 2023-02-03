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
    private String content;

    @Enumerated(EnumType.STRING)
    private InterviewCategory interviewCategory;

}
