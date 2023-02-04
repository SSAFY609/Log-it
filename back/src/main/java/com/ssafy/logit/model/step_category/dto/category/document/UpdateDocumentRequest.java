package com.ssafy.logit.model.step_category.dto.category.document;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class UpdateDocumentRequest {

    @Max(300)
    private String question;
    @Max(2000)
    private String content;
}
