package com.ssafy.logit.model.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultStatisticsDto {
    private String name;
    private List<GroupByDto> data;
}
