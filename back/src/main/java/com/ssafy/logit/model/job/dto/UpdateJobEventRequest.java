package com.ssafy.logit.model.job.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.ResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@Schema(description = "취업 여정이벤트 생성요청")
@AllArgsConstructor
public class UpdateJobEventRequest {
    @Column(length = 30)
    @Schema(description = "회사이름")
    @NotBlank
    private String companyName;


    @Schema(description = "결과")
    private ResultStatus resultStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "시작 날짜")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "종료 날짜")
    private LocalDate endDate;

}
