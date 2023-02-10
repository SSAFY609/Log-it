package com.ssafy.logit.model.job.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Schema(description = "취업여정 이벤트 생성 요청")
public class CreateJobEventRequest {

    @Column(length = 30)
    @Schema(description = "회사이름")
    @NotBlank
    private String companyName;

    @Column(length = 30)
    @Schema(description = "채용 유형",example = "인턴")
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "시작 날짜")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "종료 날짜")
    private LocalDate endDate;

//    private List<>
}
