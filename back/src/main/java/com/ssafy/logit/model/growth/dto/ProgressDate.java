package com.ssafy.logit.model.growth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class ProgressDate {

    @NotBlank
    @Schema(description = "글 작성 날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate date;

    public static ProgressDate createEventDate(LocalDate date){
        ProgressDate progressDate = new ProgressDate();
        progressDate.setDate(date);
        return progressDate;
    }
}
