package com.ssafy.logit.model.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassOrFailDto {
    private Long pass;
    private Long fail;
}
