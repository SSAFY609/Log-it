package com.ssafy.logit.model.search.Repository;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<JobEvent, Long> {

    @Query(value = "select * from jobevent where user_id = ?1 and company_name like %?2%", nativeQuery = true)
    public Optional<List<JobEvent>> searchCompany(long userId, String keyword);

    @Query(value = "select * from document where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and question like %?2%", nativeQuery = true)
    public Optional<List<Document>> searchDocQuestion(long userId, String keyword);

    @Query(value = "select * from document where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and answer like %?2%", nativeQuery = true)
    public Optional<List<Document>> searchDocAnswer(long userId, String keyword);

    @Query(value = "select * from coding_test where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and content like %?2%", nativeQuery = true)
    public Optional<List<CodingTest>> searchCodingContent(long userId, String keyword);

    @Query(value = "select * from interview where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and question like %?2%", nativeQuery = true)
    public Optional<List<Interview>> searchInterviewQuestion(long userId, String keyword);

    @Query(value = "select * from interview where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and answer like %?2%", nativeQuery = true)
    public Optional<List<Interview>> searchInterviewAnswer(long userId, String keyword);

    @Query(value = "select * from step_etc where step_category_id in (select step_category_id from step_category where jobevent_id in (select jobevent_id from jobevent where user_id = ?1)) and content like %?2%", nativeQuery = true)
    public Optional<List<StepEtc>> searchEtcContent(long userId, String keyword);
}
