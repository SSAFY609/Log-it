package com.ssafy.logit.model.step_category.entity.category;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;

@Entity
@Getter
public class StepEtc {
    @Id
    @GeneratedValue
    @Column(name = "stepetc_id")
    private Long id;

    @Max(3000)
    private String content;


}
