package com.ssafy.logit.model.step_category.dto.category.interview;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategoryDeserializer;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategorySerializer;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class CreateInterviewRequest {


    @NotNull
    private Long stepId;
    @Max(200)
    private String question;
    @Max(1000)
    private String answer;

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
