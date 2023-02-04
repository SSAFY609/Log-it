package com.ssafy.logit.model.step_category.dto.category.interview;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategoryDeserializer;
import com.ssafy.logit.model.step_category.dto.serializer.InterviewCategorySerializer;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Data
public class InterviewRequest {


    @NotNull
    private Long stepId;
    @Max(200)
    private String question;
    @Max(1000)
    private String content;

    private InterviewCategory interviewCategory;


    @JsonSerialize(using = InterviewCategorySerializer.class)
    public InterviewCategory getInterviewCategory() {
        return interviewCategory;
    }

    @JsonDeserialize(using = InterviewCategoryDeserializer.class)
    public void setInterviewCategory(InterviewCategory interviewCategory) {
        this.interviewCategory = interviewCategory;
    }
}
