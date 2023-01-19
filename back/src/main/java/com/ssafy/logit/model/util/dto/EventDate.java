package com.ssafy.logit.model.util.dto;


import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class EventDate {


    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
