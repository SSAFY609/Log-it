package com.ssafy.logit.model.step_category.entity.category;

import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;


@Entity
@Getter
public class CodingTest extends StepCategory {

    @Id
    @GeneratedValue
    private Long id;

    @Max(1000)
    private String content;

    @Max(30)
    private String algoCategory;


}
