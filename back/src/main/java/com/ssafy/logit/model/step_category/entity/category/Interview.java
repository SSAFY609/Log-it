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

    // private setter
    private void setStepCategory(StepCategory stepCategory) {
        if(stepCategory!=null){
        this.stepCategory = stepCategory;
        }
        throw new IllegalStateException();
    }

    private void setName(String name) {
        this.name = name;
    }


    // 생성 메소드 //
    public static Interview create(StepCategory stepCategory, String name){
        Interview interview = new Interview();
        interview.setStepCategory(stepCategory);
        interview.setName(name);
        return interview;
    }

    // 수정 메소드 //
    public Interview update(String name){
        this.setName(name);
        return this;
    }

}
