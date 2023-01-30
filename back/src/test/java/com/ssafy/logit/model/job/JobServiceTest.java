package com.ssafy.logit.model.job;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import com.ssafy.logit.model.user.repository.UserRepository;
import com.ssafy.logit.model.util.dto.EventDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class JobServiceTest {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepo;
    @BeforeEach
    void initInstance(){
        User user = new User( 1L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user2 = new User(2L,"test2", "abc@abc.com", "1234", 8, "123", null,false ,null);

        userRepo.save(user);
        userRepo.save(user2);
    }
    @Test
    @DisplayName("취업 성장이벤트 생성")
    public void 취업이벤트_생성() throws Exception{
        //given
        User user1 = userRepo.findById(1L).get();
        User user2 = userRepo.findById(2L).get();



        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", false, EventDate.createEventDate(now, tommorow));
        JobEvent jobEvent2 = JobEvent.createJobEvent(user2, "company2", "인턴", false, EventDate.createEventDate(now, tommorow));

        jobRepository.save(jobEvent1);
        jobRepository.save(jobEvent2);


        //then
        long count = jobRepository.count();
        Assertions.assertThat(count).isEqualTo(2L);

    }




}