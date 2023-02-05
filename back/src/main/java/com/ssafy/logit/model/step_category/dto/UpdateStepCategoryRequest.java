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

@Schema(description = "채용 전형 수정 요청")
@Data
public class UpdateStepCategoryRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "채용 전형 날짜")
    private LocalDate typeDate;
    @Schema(description = "채용 전형 상황",example = "PASS")
    private ResultStatus resultStatus;

}
