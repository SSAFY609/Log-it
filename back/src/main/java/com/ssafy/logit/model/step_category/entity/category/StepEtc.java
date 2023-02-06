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


    // private setter //
    private void addStepCategory(StepCategory stepCategory) {
        stepCategory.getStepEtcList().add(this);
        this.stepCategory = stepCategory;
    }

    private void setContent(String content) {
        this.content = content;
    }


    // 생성 메소드 //
    public static StepEtc create(StepCategory stepCategory, String content) {
        StepEtc stepEtc = new StepEtc();
        stepEtc.addStepCategory(stepCategory);
        stepEtc.setContent(content);
        return stepEtc;
    }

    // 수정 메소드 //
    public StepEtc update(String content) {
        this.setContent(content);
        return this;
    }
}
