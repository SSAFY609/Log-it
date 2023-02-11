package com.ssafy.logit.model.step_category.dto.category.entire;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "채용전형에 대한 리스트 생성/수정 id가 있어야 수정")
public class StepCategoryAllRequest {
    private Long stepId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "채용 전형 날짜")
    private LocalDate typeDate;

    @Schema(description = "채용 전형 상황",example = "PASS")
    private String resultStatus;

    @Schema(description = "전형 분류",example = "CODING")
    private String jobCategory;

    private List<AllCategoryRequest> list;

}
