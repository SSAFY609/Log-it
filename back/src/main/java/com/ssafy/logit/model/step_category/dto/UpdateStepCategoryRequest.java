package com.ssafy.logit.model.step_category.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.ResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
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
    @Schema(description = "이름 작성",example = "코테 뿌셔버리기")
    @Max(30)
    private String name;

}
