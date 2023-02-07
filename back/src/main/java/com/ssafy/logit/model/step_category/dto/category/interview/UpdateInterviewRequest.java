package com.ssafy.logit.model.step_category.dto.category.interview;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategoryDeserializer;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategorySerializer;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@Schema(description = "채용전형(면접) 수정 요청")
public class UpdateInterviewRequest {

    @Max(200)
    @Schema(description = "질문")
    private String question;
    @Max(1000)
    @Schema(description = "대답")
    private String answer;

    @Schema(description = "면접 유형")
    private InterviewCategory category;


    @JsonSerialize(using = InterviewCategorySerializer.class)
    public InterviewCategory getInterviewCategory() {
        return category;
    }

    @JsonDeserialize(using = InterviewCategoryDeserializer.class)
    public void setInterviewCategory(InterviewCategory category) {
        this.category = category;
    }
}
