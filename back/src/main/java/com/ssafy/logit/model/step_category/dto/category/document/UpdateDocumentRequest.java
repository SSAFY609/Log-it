package com.ssafy.logit.model.step_category.dto.category.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@Schema(description = "서류 수정 요청")
public class UpdateDocumentRequest {

    private Long documentId;
    @Max(300)
    @Schema(description = "질문")
    private String question;
    @Max(4000)
    @Schema(description = "대답")
    private String answer;
}
