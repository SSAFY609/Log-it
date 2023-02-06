package com.ssafy.logit.model.step_category.service.category;

import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.interview_detail.CreateInterviewDetailRequest;
import com.ssafy.logit.model.step_category.dto.category.interview_detail.UpdateInterviewDetailRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.entity.category.InterviewDetail;
import com.ssafy.logit.model.step_category.repository.category.InterviewDetailRepository;
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
public class InterviewDetailService {
    private final InterviewDetailRepository interviewDetailRepository;

    @Transactional
    public InterviewDetail create(User user, Interview interview, CreateInterviewDetailRequest request){
        checkUser(user, interview);
        InterviewDetail interviewDetail = InterviewDetail.create(interview, request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        InterviewDetail saveInterview = interviewDetailRepository.save(interviewDetail);
        return saveInterview;
    }


    @Transactional
    public InterviewDetail update(User user, Long id, UpdateInterviewDetailRequest request){
        InterviewDetail interviewDetail = interviewDetailRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user,interviewDetail);
        InterviewDetail updateInterview = interviewDetail.update(request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        return updateInterview;
    }

    @Transactional
    public void delete(User user, Long id){
        InterviewDetail interviewDetail = interviewDetailRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, interviewDetail);
        interviewDetailRepository.delete(interviewDetail);
    }


    public InterviewDetail get(Long id){
        InterviewDetail interviewDetail = interviewDetailRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return interviewDetail;
    }


    // 카테고리 체크
    private void checkCategory(StepCategory stepCategory) {
        if(stepCategory.getJobCategory()!= JobCategory.INTERVIEW){
            throw new WrongCategoryException();
        }
    }


    // User 체크
    private void checkUser(User user, InterviewDetail interviewDetail) {
        if(user != interviewDetail.getInterview().getStepCategory().getJobEvent().getUser()){
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
