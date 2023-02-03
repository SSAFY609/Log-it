package com.ssafy.logit.model.step_category.entity.category;


import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_category_id")
    private StepCategory stepCategory;

    @Max(300)
    private String question;

    @Max(2000)
    private String content;

    private void setId(Long id) {
        this.id = id;
    }

    private void setStepCategory(StepCategory stepCategory) {
        if(stepCategory ==null){
            throw new IllegalStateException();
        }
        this.stepCategory = stepCategory;
    }
    private void setQuestion(String question) {
        this.question = question;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public static Document create( StepCategory stepCategory, String question, String content){
        Document document = new Document();
        document.setStepCategory(stepCategory);
        document.setQuestion(question);
        document.setContent(content);

        return  document;
    }

    public Document update(String question,String content ){
        this.setQuestion(question);
        this.setContent(content);
        return this;
    }

}
