package com.ssafy.logit.model.job.dto;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Data
@Schema(description = "취업 여정이벤트 생성요청")
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobEventRequest {
    @Column(length = 30)
    @Schema(description = "회사이름")
    @NotBlank
    private String companyName;

    @Column(length = 30)
    @Schema(description = "채용 유형",example = "인턴")
    private String type;

    @Schema(description = "결과")
    private ResultStatus resultStatus;

    private EventDate eventDate;
}
