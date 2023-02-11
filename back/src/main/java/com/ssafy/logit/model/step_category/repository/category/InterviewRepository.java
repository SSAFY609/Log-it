package com.ssafy.logit.model.step_category.repository.category;

import com.ssafy.logit.model.step_category.dto.category.interview.InterviewCategoryStatistics;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview,Long> {
    @Query(" select new com.ssafy.logit.model.step_category.dto.category.interview.InterviewCategoryStatistics(i.interviewCategory,count(i)) " +
            " from Interview i" +
            " group by i.interviewCategory " +
            " order by count(i) desc")
    List<InterviewCategoryStatistics> getGroupByInterviewCategory();

    Page<Interview> findAll(Pageable pageable);

}
