package com.ssafy.logit.model.job.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public List<JobEvent> getEvents(User user) {
        List<JobEvent> result = jobRepository.findAllByUser(user);
        return result;

    }

    @Transactional
    public JobEvent create(User user, CreateJobEventRequest request) {


        String type = request.getType();
        String companyName = request.getCompanyName();
        EventDate eventDate = EventDate.createEventDate(request.getStartDate(), request.getEndDate());
        JobEvent jobEvent = JobEvent.createJobEvent(user, companyName, type, eventDate);
        JobEvent saveEvent = jobRepository.save(jobEvent);

        return saveEvent;
    }



    @Transactional
    public JobEvent update(User user, Long jobEventId, UpdateJobEventRequest request) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        checkUser(user,jobEvent);
        String companyName = request.getCompanyName();
        String type = request.getType();
        ResultStatus resultStatus = request.getResultStatus();
        EventDate eventDate = request.getEventDate();
        JobEvent updateEvent = jobEvent.updateInfo(companyName, type, resultStatus, eventDate);

        return updateEvent;
    }

    @Transactional
    public void delete(User user, Long jobEventId) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        checkUser(user, jobEvent);
        jobRepository.delete(jobEvent);
    }


    public CreateJobEventResponse get(Long jobEventId) {
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        return new CreateJobEventResponse(jobEvent);
    }

    private static void checkUser(User user, JobEvent jobEvent) {
        if (!jobEvent.getUser().equals(user)) {
            throw new DifferentUserException();
        }
    }

//    @Transactional
//    public JobEvent create(Long userId, CreateJobEventRequest jobEvent) {
//        User user = userRepo.findById(userId).get();
////        JobEvent jobEvent = JobEvent.createJobEvent(user, jobEvent.)
//        jobRepository.finb
//
//    }


//    @Transactional
//    public void save(Long userId, JobEvent jobEvent) {
//        User user = userRepo.findById(userId).get();
//        jobRepository.save(user, jobEvent);
//
//    }
}
