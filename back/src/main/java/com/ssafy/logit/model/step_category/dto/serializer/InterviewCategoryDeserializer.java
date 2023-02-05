package com.ssafy.logit.model.step_category.dto.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;

import java.io.IOException;

public class InterviewCategoryDeserializer extends JsonDeserializer<InterviewCategory> {
    @Override
    public InterviewCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return InterviewCategory.valueOf(p.getValueAsString().toUpperCase());
    }
}
