package com.ssafy.logit.model.job;

import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.job.service.JobService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class JobServiceTest {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobService jobService;


    @BeforeEach
    void initInstance() {
        User user = new User(1L, "test1", "abc@abc.com", "1234", 8, "123", null, false, null);
        User user2 = new User(2L, "test2", "abcd@abc.com", "1234", 8, "123", null, false, null);

        userRepository.save(user);
        userRepository.save(user2);
    }

    @Test
    @DisplayName("취업 이벤트 생성")
    public void 취업이벤트_생성() throws Exception {
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();
        User user2 = userRepository.findByEmail("abcd@abc.com").get();


        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company3", "신입",now,tommorow);
        JobEvent jobEvent2 = JobEvent.createJobEvent(user2, "company4", "인턴", now,tommorow);

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

    @Test
    @DisplayName("취업 이벤트 수정")
    public void 이벤트수정() throws Exception {
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();


        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        jobRepository.save(jobEvent1);
        jobEvent1.updateInfo("회사2", "경력", ResultStatus.INPROGRESS, tommorow, tommorow.plusDays(1));


    }

    @Test
    @DisplayName("취업 이벤트 삭제")
    public void 이벤트삭제() throws Exception {
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();

        //when
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", now,tommorow);
        jobRepository.save(jobEvent1);

        jobRepository.delete(jobEvent1);
        System.out.println("jobRepository = " + jobRepository.count());
        Assertions.assertThat(jobRepository.count()).isEqualTo(0);

    }


    @Test
    @DisplayName("한 유저에 대한 취업 이벤트 개수 확인")
    public void 유저에_대한_이벤트리스트() throws Exception {
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();
        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);
        //when
        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent jobEvent2 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent jobEvent3 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent jobEvent4 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent jobEvent5 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);

        jobRepository.save(jobEvent1);
        jobRepository.save(jobEvent2);
        jobRepository.save(jobEvent3);
        jobRepository.save(jobEvent4);
        jobRepository.save(jobEvent5);
        //then
        Assertions.assertThat(jobRepository.count()).isEqualTo(5);
    }


    @Test
    @DisplayName("다른 유저가 이벤트 수정 시도")
    public void 다른유저_이벤트수정_시도() throws Exception{
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();
        User user2 = userRepository.findByEmail("abcd@abc.com").get();

        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent save = jobRepository.save(jobEvent1);
        Long jobEventID = save.getId();
        //when
        UpdateJobEventRequest request = new UpdateJobEventRequest("회사(UPdated)", "type(updated)", ResultStatus.PASS, now, tommorow);

        //then
        //다른 유저가 업데이트 시도
        assertThrows(DifferentUserException.class, () -> jobService.update(user2, jobEventID, request));
    }

    @Test
    @DisplayName("다른 유저가 이벤트 삭제 시도")
    public void 다른유저_이벤트삭제_시도() throws Exception{
        //given
        User user1 = userRepository.findByEmail("abc@abc.com").get();
        User user2 = userRepository.findByEmail("abcd@abc.com").get();

        LocalDate now = LocalDate.now();
        LocalDate tommorow = LocalDate.now().plusDays(1);

        //when
        JobEvent jobEvent1 = JobEvent.createJobEvent(user1, "company1", "신입", now, tommorow);
        JobEvent save = jobRepository.save(jobEvent1);
        Long jobEventID = save.getId();


        //then
        //다른 유저가 업데이트 시도
        assertThrows(DifferentUserException.class, () -> jobService.delete(user2, jobEventID));
    }

}