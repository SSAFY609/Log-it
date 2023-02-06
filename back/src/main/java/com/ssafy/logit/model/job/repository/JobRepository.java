package com.ssafy.logit.model.job.repository;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import com.ssafy.logit.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  JobRepository extends JpaRepository<JobEvent,Long> {

    List<JobEvent> findAllByUser(User user);


//    List<JobEvent> findJobEventByEventDate




    @Query("SELECT DISTINCT iv " +
            "FROM JobEvent j " +
            "JOIN  j.stepCategoryList s " +
            "JOIN  s.interviewList iv ")
    List<Interview> getInterviewList();

    @Query("SELECT DISTINCT d " +
            "FROM JobEvent j " +
            "JOIN  j.stepCategoryList s " +
            "JOIN  s.document d")
    List<Document> findAllDocuments();

    @Query("SELECT DISTINCT etc " +
            "FROM JobEvent j " +
            "JOIN  j.stepCategoryList s " +
            " JOIN s.stepEtcList etc ")
    List<StepEtc> getStepEtc();
    @Query("SELECT DISTINCT c " +
            "FROM JobEvent j " +
            "JOIN  j.stepCategoryList s " +
            "JOIN  s.codingTestList c " )
    List<CodingTest> getCodingTest();





}

