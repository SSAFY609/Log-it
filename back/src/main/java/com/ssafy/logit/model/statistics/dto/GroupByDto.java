package com.ssafy.logit.model.statistics.dto;


import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEventStatus;
import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupByDto {
    private String name;
    private Long count;


    public  GroupByDto(ResultStatus status,Long count){
        this.name = status.getValue();
        this.count = count;
    }
    public  GroupByDto(AlgoCategory category, Long count){
        this.name = category.getValue();
        this.count = count;
    }
    public  GroupByDto(JobEventStatus status, Long count){
        this.name = status.getValue();
        this.count = count;
    }

    public  GroupByDto(InterviewCategory category, Long count){
        this.name = category.getValue();
        this.count = count;
    }


}
