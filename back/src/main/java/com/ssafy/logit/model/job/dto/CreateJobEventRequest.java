package com.ssafy.logit.model.job.dto;

import com.ssafy.logit.model.util.dto.EventDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Data
@Schema(description = "성장여정이벤트 생성요청")
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobEventRequest {

    @Column(length = 30)
    @Schema(description = "회사이름")
    private String companyName;

    @Column(length = 30)
    @Schema(description = "채용 유형",example = "인턴")
    private String type;

    @Schema(description = "결과")
    private boolean result;

    @Embedded
    private EventDate eventDate;
}
