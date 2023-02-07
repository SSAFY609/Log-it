package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.NoSuchElementException;


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
    @Enumerated(EnumType.STRING)
    private AlgoCategory algoCategory;


    // setter
    private void addStepCategory(StepCategory stepCategory) {
        if (stepCategory == null) {
            throw new NoSuchElementException();
        }
        stepCategory.getCodingTestList().add(this);
        this.stepCategory = stepCategory;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public void setAlgoCategory(AlgoCategory algoCategory) {
        this.algoCategory = algoCategory;
    }

    // 생성 메소드 //
    public static CodingTest createCodingTest(StepCategory stepCategory, String content, AlgoCategory category) {
        CodingTest codingTest = new CodingTest();
        codingTest.addStepCategory(stepCategory);
        codingTest.setContent(content);
        codingTest.setAlgoCategory(category);
        return codingTest;
    }

    // 수정 메소드 //
    public CodingTest update(String content, AlgoCategory category) {
        this.setContent(content);
        this.setAlgoCategory(category);
        return this;
    }


}
