package com.ssafy.logit.model.step_category.dto.category.document;

import com.ssafy.logit.model.step_category.entity.category.Document;
import lombok.Data;


@Data
public class DocumentResponse {

    private Long id;
    private String question;
    private String answer;

    // 생성자
    public DocumentResponse(Document document){
        this.id = document.getId();
        this.question = document.getQuestion();
        this.answer = document.getAnswer();
    }
}
