package com.ssafy.logit.model.step_category.service.category;

import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.entire.AllCategoryRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.CreateInterviewRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewCategoryStatistics;
import com.ssafy.logit.model.step_category.dto.category.interview.UpdateInterviewRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.repository.category.InterviewRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {
    private final InterviewRepository interviewRepository;

    /**
     * 면접 객체를 생성합니다.
     * @param user
     * @param stepCategory
     * @param request
     * @return
     */
    @Transactional
    public Interview create(User user, StepCategory stepCategory, CreateInterviewRequest request){
        checkUser(user, stepCategory);
        Interview interview = Interview.create(stepCategory, request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        Interview saveInterview = interviewRepository.save(interview);
        return saveInterview;
    }
    /**
     * 면접 객체를 수정합니다.
     * @param user
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public Interview update(User user, Long id, UpdateInterviewRequest request){
        Interview interview = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user,interview);
        Interview updateInterview = interview.update(request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
        return updateInterview;
    }

    /**
     * 면접 객체를 삭제합니다.
     * @param user
     * @param id
     */
    @Transactional
    public void delete(User user, Long id){
        Interview interview = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, interview);
        interviewRepository.delete(interview);
    }


    /**
     * 면접 객체를 조회합니다.
     * @param id
     * @return
     */
    public Interview get(Long id){
        Interview interviewDetail = interviewRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return interviewDetail;
    }


    /**
     * 면접 리스트를 받아 생성, 수정을 진행합니다.
     * @param user
     * @param stepCategory
     * @param list
     */
    @Transactional
    public void createUpdateAll(User user, StepCategory stepCategory, List<UpdateInterviewRequest> list) {
        checkUser(user,stepCategory);
        for (UpdateInterviewRequest request : list) {
            if(request.getInterviewId()==null){
                Interview interview = Interview.create(stepCategory, request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
                interviewRepository.save(interview);
            }else{
                update(user, request.getInterviewId(), request);
            }
        }
    }

    @Transactional
    public void createUpdateAll(StepCategory stepCategory, List<AllCategoryRequest> list) {
        for (AllCategoryRequest request : list) {
            if(request.getId()==null){
                System.out.println("request = " + request.getInterviewCategory());
                Interview interview = Interview.create(stepCategory, request.getQuestion(),request.getAnswer(),request.getInterviewCategory());
                interviewRepository.save(interview);
            }else{
                Interview interview = interviewRepository.findById(request.getId()).orElseThrow(NoSuchElementException::new);
                interview.update(request.getQuestion(), request.getAnswer(), request.getInterviewCategory());
            }
        }
    }

    /**
     * 인터뷰 통계 
     * @return
     */
    public List<InterviewCategoryStatistics> getInterviewStatics(){
        return interviewRepository.getGroupByInterviewCategory();
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
