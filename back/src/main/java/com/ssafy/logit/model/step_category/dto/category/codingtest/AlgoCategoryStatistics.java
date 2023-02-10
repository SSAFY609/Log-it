package com.ssafy.logit.model.step_category.dto.category.codingtest;

import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import lombok.Data;

@Data
public class AlgoCategoryStatistics {
    private String algoName;
    private Long cnt;

    public AlgoCategoryStatistics(AlgoCategory algoCategory, Long cnt) {
        this.algoName = algoCategory.getValue();
        this.cnt = cnt;
    }
}
