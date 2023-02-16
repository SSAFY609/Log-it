package com.ssafy.logit.model.job.dto;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.entity.JobEventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "취업여정 이벤트 생성 응답")
public class CreateJobEventResponse {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "회사이름")
    private String companyName;


    @Schema(description = "결과")
    private JobEventStatus resultStatus;

    @Schema(description = "시작 날짜")
    private LocalDate startDate;

    @Schema(description = "종료 날짜")
    private LocalDate endDate;


    public CreateJobEventResponse(JobEvent jobEvent) {
        this.id = jobEvent.getId();
        this.companyName = jobEvent.getCompanyName();
        this.resultStatus = jobEvent.getResultStatus();
        this.startDate = jobEvent.getStartDate();
        this.endDate = jobEvent.getEndDate();
    }
}
