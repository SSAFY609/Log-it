package com.ssafy.logit.model.growth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    private long progressId;

    private String writerName;

    private String writerEmail;

    private String writerImage;

    @Lob
    private String content;
}
