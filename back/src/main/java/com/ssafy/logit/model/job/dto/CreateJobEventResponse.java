package com.ssafy.logit.model.job.dto;


import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.util.dto.EventDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Data
@Schema(description = "성장여정이벤트 생성반환")
public class CreateJobEventResponse {

    @Column(length = 30)
    @Schema(description = "회사이름")
    private String companyName;
    @Column(length = 30)
    @Schema(description = "채용 유형", example = "인턴")
    private String type;

    @Schema(description = "결과")
    private boolean result;
    @Embedded
    private EventDate eventDate;

    public CreateJobEventResponse(JobEvent jobEvent) {
        this.companyName = jobEvent.getCompanyName();
        this.type = jobEvent.getType();
        this.result = jobEvent.isResult();
        this.eventDate = jobEvent.getEventDate();
    }


}
