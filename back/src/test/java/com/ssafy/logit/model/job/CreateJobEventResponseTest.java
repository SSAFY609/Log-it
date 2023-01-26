package com.ssafy.logit.model.job;

import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
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

@SpringBootTest
class CreateJobEventResponseTest {

    @Autowired
    UserRepository userRepo;
    @Autowired
    JobRepository jobRepository;

    @BeforeEach
    public void init(){
        User user = new User( 1L,"test1", "abc@abc.com", "1234", 8, "123", null, 1, null);
        User user1 = new User( 2L,"test1", "abc@abc.com", "1234", 8, "123", null, 1, null);
        User user2 = new User( 3L,"test1", "abc@abc.com", "1234", 8, "123", null, 1, null);
        User user3 = new User( 4L,"test1", "abc@abc.com", "1234", 8, "123", null, 1, null);
        User user4 = new User( 5L,"test1", "abc@abc.com", "1234", 8, "123", null, 1, null);
        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
//        System.out.println("userRepo.count() = " + userRepo.count());

    }


    @Test
    @DisplayName("생성여정 반환dto 테스트")
    public void test_createJobEventResponse() throws Exception{
        //given
        User user1 = userRepo.findById(1L).get();

        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", false, EventDate.createEventDate(now, tommorow));
        jobRepository.save(jobEvent1);

        //then
        CreateJobEventResponse cjr = new CreateJobEventResponse(jobEvent1);
        Assertions.assertThat(cjr.getCompanyName()).isEqualTo("company1");
        Assertions.assertThat(cjr.getType()).isEqualTo("신입");
    }


}