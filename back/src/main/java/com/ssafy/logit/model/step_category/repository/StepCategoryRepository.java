package com.ssafy.logit.model.step_category.repository;

import com.ssafy.logit.model.step_category.entity.StepCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepCategoryRepository extends JpaRepository<StepCategory,Long> {
    List<StepCategory> findStepCategoriesByJobEvent(Long jobEvent);
}
