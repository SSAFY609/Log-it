package com.ssafy.logit.model.step_category.dto.category.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "생성,서류 리스트 요청")
public class CreateUpdateDocumentList {
    @Schema(description = "채용 전형 id")
    private Long stepId;
    
    @Schema(description = "서류 리스트")
    private List<UpdateDocumentRequest> list;
}
