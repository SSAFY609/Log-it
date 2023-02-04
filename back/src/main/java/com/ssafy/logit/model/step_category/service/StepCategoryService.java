package com.ssafy.logit.model.step_category.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.dto.CreateStepCategoryRequest;
import com.ssafy.logit.model.step_category.dto.UpdateStepCategoryRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class StepCategoryService {
    private final StepCategoryRepository stepCategoryRepository;
    private final JobRepository jobRepository;


    @Transactional
    public StepCategory create(User user, CreateStepCategoryRequest request) {
        JobEvent jobEvent = jobRepository.findById(request.getJobEventId()).orElseThrow(NoSuchElementException::new);
        User eventUser = jobEvent.getUser();
        checkUser(user, eventUser);

        StepCategory stepCategory = StepCategory.createCategory(jobEvent, request.getTypeDate(), request.getJobCategory());
        StepCategory saveCategory = stepCategoryRepository.save(stepCategory);

        return saveCategory;
    }

    public StepCategory get(Long id) {
        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return stepCategory;
    }

    @Transactional
    public StepCategory update(User user, Long id, UpdateStepCategoryRequest request) {
        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        StepCategory updateCategory = stepCategory.updateCategory(request.getTypeDate(), request.getResultStatus());
        return updateCategory;
    }

    @Transactional
    public void delete(User user, Long id) {
        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        stepCategoryRepository.delete(stepCategory);
    }

    private void checkUser(User user, StepCategory stepCategory) {
        // 유저 체크
        if (user != stepCategory.getJobEvent().getUser()) { //해당 유저가 아닌경우
            throw new DifferentUserException();
        }
    }

    private void checkUser(User user, User eventUser) {
        if (user != eventUser) { //해당 유저가 아닌 경우
            throw new DifferentUserException();
        }
    }


}
