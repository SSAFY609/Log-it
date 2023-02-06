package com.ssafy.logit.model.step_category.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ssafy.logit.model.step_category.entity.JobCategory;

import java.io.IOException;

public class JobCateogorySerializer extends JsonSerializer<JobCategory> {
    @Override
    public void serialize(JobCategory value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.name());
    }
}
