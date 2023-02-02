package com.ssafy.logit.model.job;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class JobServiceTest {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @BeforeEach
    void initInstance(){
        User user = new User( 1L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user2 = new User(2L,"test2", "abc@abc.com", "1234", 8, "123", null,false ,null);

        userRepository.save(user);
        userRepository.save(user2);
    }
    @Test
    @DisplayName("취업 성장이벤트 생성")
    public void 취업이벤트_생성() throws Exception{
        //given
        User user1 = userRepository.findById(1L).get();
        User user2 = userRepository.findById(2L).get();



        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입",  EventDate.createEventDate(now, tommorow));
        JobEvent jobEvent2 = JobEvent.createJobEvent(user2, "company2", "인턴",  EventDate.createEventDate(now, tommorow));

        jobRepository.save(jobEvent1);
        jobRepository.save(jobEvent2);

        List<JobEvent> all = jobRepository.findAll();
        for (JobEvent jobEvent : all) {
            System.out.println("jobEvent.getCompanyName() = " + jobEvent.getCompanyName());
        }

        //then
        long count = jobRepository.count();
        Assertions.assertThat(count).isEqualTo(2L);

    }




}