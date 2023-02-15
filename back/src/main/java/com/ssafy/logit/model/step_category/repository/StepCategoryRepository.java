package com.ssafy.logit.model.step_category.repository;

import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StepCategoryRepository extends JpaRepository<StepCategory,Long> {
    List<StepCategory> findStepCategoriesByJobEvent(Long jobEvent);

    @Query("select count(sc) from StepCategory sc " +
            " join sc.jobEvent j" +
            " where j.user= :user " +
            " and sc.resultStatus = :resultStatus" +
            " and sc.jobCategory = :jobCategory")
    long countByUserCategoryAndStatus(@Param("user")User user, @Param("resultStatus")ResultStatus resultStatus, @Param("jobCategory")JobCategory jobCategory);


    @Query("select count(sc) from StepCategory sc " +
            " join sc.jobEvent j" +
            " where j.user= :user " +
            " and sc.jobCategory = :jobCategory")
    long countByUserCategory(@Param("user")User user, @Param("jobCategory")JobCategory jobCategory);


}
