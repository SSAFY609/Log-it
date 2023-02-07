package com.ssafy.logit.model.step_category.entity.category;


import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document {
    @Id
    @GeneratedValue
    @Column(name = "document_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;

    @Max(300)
    private String question;

    @Max(2000)
    private String answer;


    private void addStepCategory(StepCategory stepCategory) {
        if(stepCategory ==null){
            throw new IllegalStateException();
        }
        stepCategory.getDocumentList().add(this);
        this.stepCategory = stepCategory;
    }
    private void setQuestion(String question) {
        this.question = question;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static Document create( StepCategory stepCategory, String question, String content){
        Document document = new Document();
        document.addStepCategory(stepCategory);
        document.setQuestion(question);
        document.setAnswer(content);

        return  document;
    }

    public Document update(String question,String content ){
        this.setQuestion(question);
        this.setAnswer(content);
        return this;
    }

}
