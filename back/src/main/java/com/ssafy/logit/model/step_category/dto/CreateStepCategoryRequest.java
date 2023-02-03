package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateStepCategoryRequest {
    private Long jobEventId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate typeDate;
    private ResultStatus resultStatus;
    private JobCategory jobCategory;

}
