package com.ssafy.logit.model.step_category.dto.category.document;

import com.ssafy.logit.model.step_category.entity.category.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "서류 응답")
public class DocumentResponse {

    @Schema(description = "서류 id")
    private Long id;
    @Schema(description = "질문")
    private String question;
    @Schema(description = "대답")
    private String answer;

    // 생성자
    public DocumentResponse(Document document){
        this.id = document.getId();
        this.question = document.getQuestion();
        this.answer = document.getAnswer();
    }
}
