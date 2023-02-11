package com.ssafy.logit.model.statistics.dto;


import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
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
    public  GroupByDto(AlgoCategory category, Long cnt){
        this.name = category.getValue();
        this.cnt = cnt;
    }

    public  GroupByDto(InterviewCategory category, Long cnt){
        this.name = category.getValue();
        this.cnt = cnt;
    }


}
