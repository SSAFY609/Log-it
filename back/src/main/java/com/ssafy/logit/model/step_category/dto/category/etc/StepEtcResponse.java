package com.ssafy.logit.model.step_category.dto.category.etc;


import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import lombok.Data;

@Data
public class StepEtcResponse {

    private Long id;
    private String content;


    public StepEtcResponse(StepEtc stepEtc){
        setId(stepEtc.getId());
        setContent(stepEtc.getContent());
    }

}
