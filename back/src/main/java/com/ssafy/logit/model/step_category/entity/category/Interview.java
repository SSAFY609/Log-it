package com.ssafy.logit.model.step_category.entity.category;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
public class Interview {
    @Id
    @GeneratedValue
    private Long id;


    @Max(200)
    private String question;
    @Max(1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private InterviewCategory interviewCategory;

}
