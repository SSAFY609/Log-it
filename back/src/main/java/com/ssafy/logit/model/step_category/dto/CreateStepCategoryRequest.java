package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.dto.serializer.JobCategoryDeserializer;
import com.ssafy.logit.model.step_category.dto.serializer.JobCateogorySerializer;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Schema(description = "채용 전형 생성 요청")
public class CreateStepCategoryRequest {
    @Schema(description = "취업 여정 id")
    private Long jobEventId;
    @Schema(description = "전형 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate typeDate;
    @Schema(description = "전형 상황")
    private ResultStatus resultStatus;
    @Schema(description = "전형 분류",example = "CODING")
    private JobCategory jobCategory;


    @JsonSerialize(using = JobCateogorySerializer.class)
    public JobCategory getJobCategory() {
        return jobCategory;
    }

    @JsonDeserialize(using = JobCategoryDeserializer.class)
    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }
}
