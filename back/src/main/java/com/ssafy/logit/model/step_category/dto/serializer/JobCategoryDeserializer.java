package com.ssafy.logit.model.step_category.dto.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ssafy.logit.model.step_category.entity.JobCategory;

import java.io.IOException;

public class JobCategoryDeserializer extends JsonDeserializer<JobCategory> {
    @Override
    public JobCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return JobCategory.valueOf(p.getValueAsString().toUpperCase());
    }
}
