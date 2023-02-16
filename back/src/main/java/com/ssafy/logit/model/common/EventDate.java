package com.ssafy.logit.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.exception.WrongDateException;
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
public class EventDate {

    @NotBlank
    @Schema(description = "시작날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;


    @NotBlank
    @Schema(description = "종료날짜")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;


    //생성자
    public static EventDate createEventDate(LocalDate startDate,LocalDate endDate){
        EventDate eventDate = new EventDate();
        if(isValidate(startDate,endDate)){
            eventDate.setStartDate(startDate);
            eventDate.setEndDate(endDate);
            return eventDate;
        }
        throw new WrongDateException();
    }

    //유효성 체크
    private static boolean isValidate(LocalDate startDate, LocalDate endDate){
        return endDate.isAfter(startDate);
    }
}
