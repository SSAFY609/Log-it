package com.ssafy.logit.model.step_category.dto.category;

import com.ssafy.logit.model.step_category.entity.category.Document;
import lombok.Data;


@Data
public class DocumentResponse {

    private Long id;
    private String question;
    private String content;

    // 생성자
    public DocumentResponse(Document document){
        this.id = document.getId();
        this.question = document.getQuestion();
        this.content = document.getContent();
    }
}
