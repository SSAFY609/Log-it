package com.ssafy.logit.model.step_category.service.category;

import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.interview.CreateInterviewRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.UpdateInterviewRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.repository.category.InterviewRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {
    private final InterviewRepository interviewRepository;

    @Transactional
    public Interview create(User user, StepCategory stepCategory, CreateInterviewRequest request){
        checkUser(user, stepCategory);
        Interview interview = Interview.create(stepCategory, request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        Interview saveInterview = interviewRepository.save(interview);
        System.out.println("saveInterview = " + saveInterview.getAnswer());
        System.out.println("saveInterview = " + saveInterview.getQuestion());
        System.out.println("saveInterview.getInterviewCategory() = " + saveInterview.getInterviewCategory());
        return saveInterview;
    }


    @Transactional
    public Interview update(User user, Long id, UpdateInterviewRequest request){
        Interview interview = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user,interview);
        Interview updateInterview = interview.update(request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        return updateInterview;
    }

    @Transactional
    public void delete(User user, Long id){
        Interview interview = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, interview);
        interviewRepository.delete(interview);
    }


    public Interview get(Long id){
        Interview interviewDetail = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return interviewDetail;
    }


    // 카테고리 체크
    private void checkCategory(StepCategory stepCategory) {
        if(stepCategory.getJobCategory()!= JobCategory.INTERVIEW){
            throw new WrongCategoryException();
        }
    }


    // User 체크
    private void checkUser(User user, StepCategory stepCategory) {
        if(user != stepCategory.getJobEvent().getUser()){
            throw new DifferentUserException();
        }
    }

    // User 체크
    private void checkUser(User user, Interview interview) {
        if(user != interview.getStepCategory().getJobEvent().getUser()){
            throw new DifferentUserException();
        }
    }


}
