package com.ssafy.logit.model.step_category.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.dto.CreateStepCategoryRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        List<JobEvent> all = jobRepository.findAll();

        System.out.println("all.size() = " + all.size());
        for (JobEvent jobEvent : all) {
            System.out.println("jobEvent = " + jobEvent.getId());
        }
        System.out.println("request.getJobEventId() = " + request.getJobEventId());
        JobEvent jobEvent = jobRepository.findById(request.getJobEventId()).orElseThrow(NoSuchElementException::new);
        log.info("직업 이벤트 {} ", jobEvent);
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
    public StepCategory update(User user, Long id, CreateStepCategoryRequest request) {

        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        StepCategory updateCategory = stepCategory.updateCategory(request.getTypeDate(), request.getResultStatus());
        System.out.println("updateCategory.getTypeDate() = " + updateCategory.getTypeDate());
        return updateCategory;
    }

    @Transactional
    public void delete(User user, Long id) {
        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        stepCategoryRepository.delete(stepCategory);
    }

    private void checkUser(User user, StepCategory stepCategory) {
        if (user != stepCategory.getJobEvent().getUser()) {
            throw new DifferentUserException();
        }
    }

    private void checkUser(User user, User eventUser) {
        System.out.println("user = " + user);
        System.out.println("eventUser = " + eventUser);
        System.out.println("user==eventUser = " + (user == eventUser));
        if (user != eventUser) { //해당 유저가 아닌 경우
            throw new DifferentUserException();
        }
    }


}
