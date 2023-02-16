package com.ssafy.logit.model.step_category.dto;

import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "채용전형 dto")
public class StepCategoryDto {

    @Schema(description = "채용전형 id")
    private Long stepId;

    @Schema(description = "날짜")
    private LocalDate typeDate;

    @Schema(description = "결과 상태")
    private String resultStatus;

    @Schema(description = "취업 전형 분류")
    private String jobCategory;
    
    @Schema(description = "목록")
    private Object list;


    public StepCategoryDto(StepCategory stepCategory, Object o){
        this.stepId = stepCategory.getId();
        this.typeDate = stepCategory.getTypeDate();
        this.resultStatus = stepCategory.getResultStatus().getValue();
        this.jobCategory = stepCategory.getJobCategory().getJobCategory();
        this.list = o;
    }


}
