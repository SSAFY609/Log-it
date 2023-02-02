package com.ssafy.logit.model.job.dto;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Data
@Schema(description = "취업여정 이벤트 생성 응답")
public class CreateJobEventResponse {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "회사이름")
    private String companyName;

    @Schema(description = "채용 유형", example = "인턴")
    private String type;

    @Schema(description = "결과")
    private ResultStatus resultStatus;

    @Embedded
    private EventDate eventDate;

    public CreateJobEventResponse(JobEvent jobEvent) {
        this.id = jobEvent.getId();
        this.companyName = jobEvent.getCompanyName();
        this.type = jobEvent.getType();
        this.resultStatus = jobEvent.getResultStatus();
        this.eventDate = jobEvent.getEventDate();
    }
}
