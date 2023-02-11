package com.ssafy.logit.model.step_category.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.dto.CreateStepCategoryRequest;
import com.ssafy.logit.model.step_category.dto.UpdateStepCategoryRequest;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CodingTestResponse;
import com.ssafy.logit.model.step_category.dto.category.document.DocumentResponse;
import com.ssafy.logit.model.step_category.dto.category.entire.StepCategoryAllRequest;
import com.ssafy.logit.model.step_category.dto.category.etc.StepEtcResponse;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewResponse;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


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

        StepCategory stepCategory = StepCategory.createCategory(
                jobEvent,
                request.getTypeDate(),
                request.getJobCategory());
        StepCategory saveCategory = stepCategoryRepository.save(stepCategory);

        return saveCategory;
    }

    @Transactional
    public StepCategory create(JobEvent jobEvent, StepCategoryAllRequest request){
        JobCategory jobCategory = JobCategory.nameOf(request.getJobCategory());
        StepCategory stepCategory = StepCategory.createCategory(
                jobEvent,
                request.getTypeDate(),
                jobCategory
        );
        StepCategory saveCategory = stepCategoryRepository.save(stepCategory);
        return saveCategory;
    }

    @Transactional
    public void create(JobEvent jobEvent, JobCategory jobCategory){
        StepCategory category = StepCategory.createCategory(jobEvent, null, jobCategory);
        stepCategoryRepository.save(category);
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
    public StepCategory update(StepCategoryAllRequest request) {
        StepCategory stepCategory = stepCategoryRepository.findById(request.getStepId()).orElseThrow(NoSuchElementException::new);
        ResultStatus resultStatus = ResultStatus.nameOf(request.getResultStatus());
        StepCategory updateCategory = stepCategory.updateCategory(request.getTypeDate(), resultStatus);
        return updateCategory;
    }


    @Transactional
    public void delete(User user, Long id) {
        StepCategory stepCategory = stepCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        stepCategoryRepository.delete(stepCategory);
    }

    public List<StepCategory> findStepCategories(JobEvent jobEvent) {
        List<StepCategory> stepCategoryList = jobEvent.getStepCategoryList();
        return stepCategoryList;
    }

    public Object getStepCategory(StepCategory stepCategory) {
        switch (stepCategory.getJobCategory()) {
            case DOCUMENT:
                List<DocumentResponse> documentResponseList = stepCategory.getDocumentList()
                        .stream()
                        .map(o -> new DocumentResponse(o))
                        .collect(Collectors.toList());
                return documentResponseList;
            case ETC:
                List<StepEtcResponse> stepEtcListResponse = stepCategory.getStepEtcList()
                        .stream()
                        .map(o -> new StepEtcResponse(o))
                        .collect(Collectors.toList());
                return stepEtcListResponse;
            case INTERVIEW:
                List<InterviewResponse> interviewList = stepCategory.getInterviewList()
                        .stream()
                        .map(o->new InterviewResponse(o))
                        .collect(Collectors.toList());
                return interviewList;

            case CODINGTEST:
                List<CodingTestResponse> codingTest = stepCategory.getCodingTestList()
                        .stream()
                        .map(o -> new CodingTestResponse(o))
                        .collect(Collectors.toList());
                return codingTest;
        }

        throw new NoSuchElementException();

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
