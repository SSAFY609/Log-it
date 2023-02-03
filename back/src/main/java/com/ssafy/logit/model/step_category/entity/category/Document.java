package com.ssafy.logit.model.step_category.entity.category;


import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;

@Entity
@Getter
public class Document {
    @Id
    @GeneratedValue
    @Column(name = "document_id")
    private Long id;
    @Max(300)
    private String question;

    @Max(2000)
    private String content;
}
