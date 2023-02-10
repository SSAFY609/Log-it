package com.ssafy.logit.model.job.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongDateException;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final JobRepository jobRepository;

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
        ResultStatus resultStatus = request.getResultStatus();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        JobEvent updateEvent = jobEvent.updateInfo(companyName,  resultStatus, startDate,endDate);

        return updateEvent;
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
