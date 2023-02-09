package com.ssafy.logit.model.step_category.repository.category;

import com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodingTestRepository extends JpaRepository<CodingTest,Long> {


    @Query("select " +
            " new com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics(ct.algoCategory,count(ct) ) " +
            " from CodingTest ct" +
            " group by ct.algoCategory" +
            " order by count(ct) desc")
    List<AlgoCategoryStatistics> getGroupByAlgoCategory();
}
