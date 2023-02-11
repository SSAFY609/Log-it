package com.ssafy.logit.model.statistics.dto;


import com.ssafy.logit.model.common.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupByDto {
    private String name;
    private Long cnt;


    public  GroupByDto(ResultStatus status,Long cnt){
        this.name = status.getValue();
        this.cnt = cnt;
    }
}
