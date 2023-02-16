package com.ssafy.logit.model.step_category.dto.category.codingtest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "생성,수정 코테 리스트 요청")
public class CreateUpdateCodingTestList {

    @Schema(description = "채용 전형 id")
    private Long stepId;

    @Schema(description = "코테 리스트")
    private List<UpdateCodingTestRequest> list;

}