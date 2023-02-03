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
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;

    @Max(50)
    private String name;

}
