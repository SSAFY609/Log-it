package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Schema(description = "채용 전형 생성 반환")
public class CreateStepCategoryResponse {


    @Schema(description = "채용 전형 id")
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "채용 전형 날짜")
    private LocalDate typeDate;
    @Schema(description = "채용 전형 상황")
    private ResultStatus resultStatus;
    @Schema(description = "채용 전형 분류",example = "INTERVIEW")
    private JobCategory jobCategory;

    @Schema(description = "이름", example = "코테 부셔져버렸다...")
    private String name;
    public  CreateStepCategoryResponse(StepCategory stepCategory) {
        this.id = stepCategory.getId();
        this.typeDate = stepCategory.getTypeDate();
        this.resultStatus = stepCategory.getResultStatus();
        this.jobCategory = stepCategory.getJobCategory();
        this.name = stepCategory.getName();
    }


}
