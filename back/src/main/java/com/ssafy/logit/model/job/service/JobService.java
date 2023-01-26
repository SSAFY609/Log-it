package com.ssafy.logit.model.job.service;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepo;
import com.ssafy.logit.model.util.dto.EventDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.security.acl.NotOwnerException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepo userRepo;

    public List<JobEvent> getEvents(Long userId){
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        List<JobEvent> result = jobRepository.findAllByUser(user);
        for (JobEvent jobEvent : result) {
            System.out.println("jobEvent = " + jobEvent);
        }
        return result;

    }

    @Transactional
    public JobEvent create(Long userId, CreateJobEventRequest createJobEventRequest) {
        Optional<User> byId = userRepo.findById(userId);
        User user = userRepo.findById(userId).get();


        String type = createJobEventRequest.getType();
        String companyName = createJobEventRequest.getCompanyName();
        boolean result = createJobEventRequest.isResult();
        EventDate eventDate = createJobEventRequest.getEventDate();
        JobEvent jobEvent = JobEvent.createJobEvent(user, companyName, type, result, eventDate);

        JobEvent saveEvent = jobRepository.save(jobEvent);
        return saveEvent;
    }

    @Transactional
    public JobEvent update(Long userId,Long jobEventId,CreateJobEventRequest createJobEventRequest){
        User user = userRepo.findById(userId).get();
        JobEvent jobEvent = jobRepository.findById(jobEventId).get();
        if(user != jobEvent.getUser()){
            throw new DifferentUserException();
        }
        String companyName = createJobEventRequest.getCompanyName();
        String type = createJobEventRequest.getType();
        boolean result = createJobEventRequest.isResult();
        EventDate eventDate = createJobEventRequest.getEventDate();
        JobEvent updateEvent = jobEvent.updateInfo(companyName, type, result, eventDate);

        return updateEvent;
    }

    @Transactional
    public void delete(Long userId,Long jobEventId){
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        if(!jobEvent.getUser().equals(user)){
            throw new DifferentUserException();
        }
        jobRepository.delete(jobEvent);
    }

    public CreateJobEventResponse get(Long jobEventId){
        JobEvent jobEvent = jobRepository.findById(jobEventId).orElseThrow(NoSuchElementException::new);
        return new CreateJobEventResponse(jobEvent);
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
