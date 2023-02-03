package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateStepCategoryResponse {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate typeDate;
    private ResultStatus resultStatus;
    private JobCategory jobCategory;
    public  CreateStepCategoryResponse(StepCategory stepCategory) {
        this.id = stepCategory.getId();
        this.typeDate = stepCategory.getTypeDate();
        this.resultStatus = stepCategory.getResultStatus();
        this.jobCategory = stepCategory.getJobCategory();
    }


}
