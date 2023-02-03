package com.ssafy.logit.model.step_category.dto;


import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateStepCategoryRequest {
    private Long jobEventId;
    private LocalDate typeDate;
    private ResultStatus resultStatus;
    private JobCategory jobCategory;

}
