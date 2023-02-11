package com.ssafy.logit.model.job.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
@Data
public class CreateJobEventCategoryRequest {

    @Column(length = 30)
    @Schema(description = "회사이름")
    @NotBlank
    private String companyName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "시작 날짜")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "종료 날짜")
    private LocalDate endDate;

    @Schema(description = "전형 리스트")
    private List<String> jobCategoryList;

}
