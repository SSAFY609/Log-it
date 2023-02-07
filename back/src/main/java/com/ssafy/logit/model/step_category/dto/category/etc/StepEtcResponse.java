package com.ssafy.logit.model.step_category.dto.category.etc;


import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "채용전형(기타) 응답")
public class StepEtcResponse {

    @Schema(description = "기타 id")
    private Long id;
    @Schema(description = "내용")
    private String content;


    public StepEtcResponse(StepEtc stepEtc){
        setId(stepEtc.getId());
        setContent(stepEtc.getContent());
    }

}
