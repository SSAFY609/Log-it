package com.ssafy.logit.model.util.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@Schema
public class EventDate {

    @Schema(description = "생성날짜")
    private LocalDate startDate;
    @Schema(description = "종료날짜")
    private LocalDate endDate;



    //생성자
    public static EventDate createEventDate(LocalDate startDate,LocalDate endDate){
        EventDate eventDate = new EventDate();
        if(isValidate(startDate,endDate)){
            eventDate.setStartDate(startDate);
            eventDate.setEndDate(endDate);
            return eventDate;
        }
        return null;
    }

    private static boolean isValidate(LocalDate startDate, LocalDate endDate){
        return endDate.isAfter(startDate);
    }


}
