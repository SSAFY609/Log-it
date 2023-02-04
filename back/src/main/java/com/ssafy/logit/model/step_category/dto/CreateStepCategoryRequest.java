package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.dto.serializer.JobCategoryDeserializer;
import com.ssafy.logit.model.step_category.dto.serializer.JobCateogorySerializer;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateStepCategoryRequest {
    private Long jobEventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate typeDate;
    private ResultStatus resultStatus;
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
