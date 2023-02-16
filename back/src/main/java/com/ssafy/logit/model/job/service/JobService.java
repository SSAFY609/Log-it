package com.ssafy.logit.model.job.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.NotPassException;
import com.ssafy.logit.exception.WrongDateException;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.dto.CreateJobEventCategoryRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.entity.JobEventStatus;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.dto.category.entire.AllCategoryRequest;
import com.ssafy.logit.model.step_category.dto.category.entire.JobEventAllRequest;
import com.ssafy.logit.model.step_category.dto.category.entire.StepCategoryAllRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.CodingTestService;
import com.ssafy.logit.model.step_category.service.category.DocumentService;
import com.ssafy.logit.model.step_category.service.category.InterviewService;
import com.ssafy.logit.model.step_category.service.category.StepEtcService;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final JobRepository jobRepository;
    private final StepCategoryService stepCategoryService;
    private final CodingTestService codingTestService;
    private final StepEtcService stepEtcService;
    private final InterviewService interviewService;
    private final DocumentService documentService;

    /**
     * 유저에 대한 모든 취업 이벤트 반환
     * @param user
     * @return
     */
    public List<JobEvent> getEvents(User user) {
        List<JobEvent> result = jobRepository.findAllByUser(user);
        return result;
    }

    /**
     * request을 입력받아 취업이벤트 생성
     * @param user
     * @param request
     * @return
     */
    @Transactional
    public JobEvent create(User user, CreateJobEventRequest request) {
        String companyName = request.getCompanyName();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        if(endDate!=null){
            validateDate(startDate,endDate);
        }
        JobEvent jobEvent = JobEvent.createJobEvent(user, companyName, startDate,endDate);
        JobEvent saveEvent = jobRepository.save(jobEvent);

        return saveEvent;
    }


    /**
     * 취업이벤트 id와 request을 입력받아 취업이벤트 수정
     * @param user
     * @param jobEventId
     * @param request
     * @return
     */
    @Transactional
    public JobEvent update(User user, Long jobEventId, UpdateJobEventRequest request) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        checkUser(user,jobEvent);
        String companyName = request.getCompanyName();
        JobEventStatus resultStatus = request.getResultStatus();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        JobEvent updateEvent = jobEvent.updateInfo(companyName,  resultStatus, startDate,endDate);

        return updateEvent;
    }

    @Transactional
    public JobEvent createAll(User user, CreateJobEventCategoryRequest request){
        CreateJobEventRequest createJobEventRequest = new CreateJobEventRequest(request.getCompanyName(), request.getStartDate(), request.getEndDate());

        JobEvent jobEvent = create(user, createJobEventRequest);
        List<String> jobCategoryResult = request.getJobCategoryList();
        // String -> Enum 변환
        List<JobCategory> jobCategoryList = jobCategoryResult.stream()
                .map(o -> JobCategory.nameOf(o))
                .collect(Collectors.toList());
        for (JobCategory jobCategory : jobCategoryList) {
            stepCategoryService.create(jobEvent, jobCategory);
        }
        return jobEvent;
    }

    /**
     * id을 이용하여 JobEvnet을 반환합니다.
     * @param jobEventId
     * @return
     */
    public JobEvent getOne(Long jobEventId){
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        return jobEvent;
    }


    /**
     * 사용자의 취업 이벤트를 시작날짜를 기준으로 오름차순 정렬
     * @param user
     * @return
     */
    public List<JobEvent> getAllByStartDate(User user){
        List<JobEvent> jobEvents = jobRepository.findAllByUserOrderByStartDate(user);
        return jobEvents;
    }

    /**
     * 취업여정 이벤트 삭제(유저 체크)
     *
     * @param user
     * @param jobEventId
     */
    @Transactional
    public void delete(User user, Long jobEventId) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        checkUser(user, jobEvent);
        jobRepository.delete(jobEvent);
    }

    /**
     * 취업여정 이벤트를 입력받아 취업이벤트 응답dto로 반환
     * @param jobEventId
     * @return
     */

    public CreateJobEventResponse get(Long jobEventId) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        return new CreateJobEventResponse(jobEvent);
    }


    /**
     * 전체 생성, 수정
     * @param user
     * @param request
     */

    @Transactional
    public void postAll(User user, JobEventAllRequest request) {
        JobEvent jobEvent = jobRepository.findById(request.getJobId()).orElseThrow(NoSuchElementException::new);
        checkUser(user,jobEvent);

        List<StepCategoryAllRequest> datas = request.getDatas();
        for (StepCategoryAllRequest stepRequest : datas) {
            StepCategory stepCategory;
            if (stepRequest.getStepId() == null) {
                stepCategory = stepCategoryService.create(jobEvent, stepRequest);
            } else {
                stepCategory = stepCategoryService.update(stepRequest);
            }
            List<AllCategoryRequest> list = stepRequest.getList();
            JobCategory jobCategory = JobCategory.nameOf(stepRequest.getJobCategory());
            switch (jobCategory) {
                case CODINGTEST:
                    codingTestService.createUpdateAll(stepCategory, list);
                    break;
                case INTERVIEW:
                    interviewService.createUpdateAll(stepCategory, list);
                    break;
                case ETC:
                    stepEtcService.createUpdateAll(stepCategory, list);
                    break;
                case DOCUMENT:
                    documentService.createUpdateAll(stepCategory, list);
                    break;
            }
        }

        // 해당 기업 합격여부 업데이트
        JobEventStatus jobResultStatus = JobEventStatus.nameOf(request.getResultStatus());
        if(jobResultStatus==JobEventStatus.COMPLETE){
            validateJobResult(jobEvent);
        }
        jobEvent.updateInfo(request.getCompanyName(), jobResultStatus, request.getStartDate(), request.getEndDate());
    }

    /**
     * 모든 채용전형의 결과가 나와야 해당 기업이 완료될 수 있다.
     * @param jobEvent
     */
    private void validateJobResult(JobEvent jobEvent){
        List<StepCategory> stepCategoryList = jobEvent.getStepCategoryList();

        for (StepCategory stepCategory : stepCategoryList) {
            if(stepCategory.getResultStatus()==ResultStatus.INPROGRESS){
                throw new NotPassException();
            }
        }

    }



    /**
     * 유저 확인 => 토큰의 유저와 비교
     * @param user
     * @param jobEvent
     */
    private void checkUser(User user, JobEvent jobEvent) {
        if (!jobEvent.getUser().equals(user)) {
            throw new DifferentUserException();
        }
    }

    /**
     *  날짜 확인 ( 종료날짜가 시작날짜보다 뒤에 있어야 함)
     * @param startDate
     * @param endDate
     */
    private void validateDate(LocalDate startDate,LocalDate endDate){
        if(startDate.isAfter(endDate)){
            throw new WrongDateException();
        }
    }


}
