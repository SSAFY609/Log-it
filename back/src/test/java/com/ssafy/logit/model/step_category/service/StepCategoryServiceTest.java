package com.ssafy.logit.model.step_category.service;

import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
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

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StepCategoryServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    StepCategoryRepository stepCategoryRepository;


    @BeforeEach
    public void init() {
        User user1 = new User(1L, "test1", "test", "1234", 8, "1234", null, false, null);
        User user2 = new User(2L, "test2", "test2", "1234", 8, "1234", null, false, null);

        JobEvent jobEvent = JobEvent.createJobEvent(user1, "company1", "type1", null);

        userRepository.save(user1);
        userRepository.save(user2);

        jobRepository.save(jobEvent);


    }

    @Test
    @DisplayName("올바른 채용전형 생성")
    public void createTest() throws Exception {
        //given
        User test = userRepository.findByEmail("test").get();
        JobEvent jobEvent = jobRepository.findById(1L).get();

        //when
        StepCategory category = StepCategory.createCategory(jobEvent, LocalDate.now(), JobCategory.DOCUMENT);
        StepCategory saveItem = stepCategoryRepository.save(category);

        StepCategory findCategory = stepCategoryRepository.findById(saveItem.getId()).get();


        //then
        Assertions.assertThat(saveItem).isEqualTo(findCategory);
        Assertions.assertThat(stepCategoryRepository.count()).isEqualTo(1);
    }


    @Test
    @DisplayName("채용전형 수정")
    @Transactional
    public void 채용전형_수정테스트() throws Exception{
        //given
        JobEvent jobEvent = jobRepository.findById(1L).get();

        StepCategory category = StepCategory.createCategory(jobEvent, LocalDate.now(), JobCategory.DOCUMENT);
        StepCategory saveItem = stepCategoryRepository.save(category);
        //when
        StepCategory findCategory = stepCategoryRepository.findById(saveItem.getId()).get();

        findCategory.updateCategory(LocalDate.now().plusDays(1),ResultStatus.FAIL);

        //then

    }




}