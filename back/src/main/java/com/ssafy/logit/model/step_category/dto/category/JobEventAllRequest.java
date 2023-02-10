package com.ssafy.logit.model.step_category.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "기업에 대한 취업 여정 리스트 생성/수정")
public class JobEventAllRequest<T>{

    @Schema(description = "시작날짜")
    private LocalDate startDate;
    @Schema(description = "종료날짜")
    private LocalDate endDate;
    @Schema(description = "크기")
    private int size;
    @Schema(description = "데이터 목록")
    private T datas;
}
