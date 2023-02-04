package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;


@Entity
@Getter
public class CodingTest {

    @Id
    @GeneratedValue
    @Column(name = "codingtest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;

    @Max(1000)
    private String content;
    @Max(30)
    private String algoCategory;


    // setter
    private void setStepCategory(StepCategory stepCategory) {
        if (stepCategory != null) {
            this.stepCategory = stepCategory;
        }
    }

    private void setContent(String content) {
        this.content = content;
    }

    private void setAlgoCategory(String algoCategory) {
        this.algoCategory = algoCategory;
    }


    // 생성 메소드 //
    public static CodingTest createCodingTest(StepCategory stepCategory, String content, String category) {
        CodingTest codingTest = new CodingTest();
        codingTest.setStepCategory(stepCategory);
        codingTest.setContent(content);
        codingTest.setAlgoCategory(category);
        return codingTest;
    }

    // 수정 메소드 //
    public CodingTest update(String content, String category) {
        this.setContent(content);
        this.setAlgoCategory(category);
        return this;
    }




}
