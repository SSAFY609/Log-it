package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
public class StepEtc {
    @Id
    @GeneratedValue
    @Column(name = "stepetc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;

    @Max(3000)
    private String content;


}
