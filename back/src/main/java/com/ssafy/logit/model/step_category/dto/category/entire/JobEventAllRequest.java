package com.ssafy.logit.model.step_category.dto.category.entire;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "기업에 대한 취업 여정 리스트 생성/수정")
public class JobEventAllRequest{

    @Schema(description = "jobEventId")
    private Long jobId;

    @Schema(description = "회사 이름")
    private String companyName;

    @Schema(description = "진행 상황")
    private String resultStatus;

    @Schema(description = "시작날짜")
    private LocalDate startDate;
    @Schema(description = "종료날짜")
    private LocalDate endDate;
    @Schema(description = "데이터 목록")
    private List<StepCategoryAllRequest> datas;
}
