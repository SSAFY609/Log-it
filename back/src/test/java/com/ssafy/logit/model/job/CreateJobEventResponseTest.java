package com.ssafy.logit.model.job;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
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

@SpringBootTest
class CreateJobEventResponseTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;

    @BeforeEach
    public void init(){
        User user = new User( 1L,"test1", "abc@abc.com", "1234", 8, "123", null, false,null);
        User user1 = new User( 2L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user2 = new User( 3L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user3 = new User( 4L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user4 = new User( 5L,"test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
//        System.out.println("userRepo.count() = " + userRepo.count());

    }


    @Test
    @DisplayName("생성여정 반환dto 테스트")
    public void test_createJobEventResponse() throws Exception{
        //given
//        User user1 = userRepository.findById(1L).get();
//
//        //when
//        LocalDate now = LocalDate.now();
//        LocalDate tommorow = LocalDate.now().plusDays(1);
//
//        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입",  EventDate.createEventDate(now, tommorow));
//        jobRepository.save(jobEvent1);
//
//        //then
//        CreateJobEventResponse cjr = new CreateJobEventResponse(jobEvent1);
//        Assertions.assertThat(cjr.getCompanyName()).isEqualTo("company1");
//        Assertions.assertThat(cjr.getType()).isEqualTo("신입");
    }


}