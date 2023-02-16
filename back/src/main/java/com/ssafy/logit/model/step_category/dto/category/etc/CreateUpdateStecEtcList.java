package com.ssafy.logit.model.step_category.dto.category.etc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "생성,수정 전형(기타) 리스트 요청")
public class CreateUpdateStecEtcList {
    @Schema(description = "채용 전형 id")
    private Long stepId;

    @Schema(description = "채용전형(기타) 리스트")
    private List<UpdateStepEtcRequest> list;
}

