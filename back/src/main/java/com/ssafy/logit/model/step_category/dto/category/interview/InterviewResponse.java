package com.ssafy.logit.model.step_category.dto.category.interview;

import com.ssafy.logit.model.step_category.entity.category.Interview;
import lombok.Data;


@Data
public class InterviewResponse {

    private  Long id;
    private String name;

    // 생성자
    public InterviewResponse(Interview interview){
        this.id= interview.getId();
        this.name = interview.getName();
    }

}
