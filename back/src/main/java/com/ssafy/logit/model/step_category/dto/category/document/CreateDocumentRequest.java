package com.ssafy.logit.model.step_category.dto.category.document;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class CreateDocumentRequest {
    @NotNull
    private Long stepId;
    @Max(300)
    private String question;
    @Max(2000)
    private String content;
}
