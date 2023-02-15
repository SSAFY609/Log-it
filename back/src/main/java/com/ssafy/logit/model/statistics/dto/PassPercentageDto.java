package com.ssafy.logit.model.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassPercentageDto {
    private Long pass;
    private Long tot;
    private double percentage;
}
