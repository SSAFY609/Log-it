package com.ssafy.logit.model.statistics.repository;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository {
    private final EntityManager em;



    public List<GroupByDto> getAlgoStatics(){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(ct.algoCategory,count(ct))" +
                        " from CodingTest ct " +
                        " group by ct.algoCategory" +
                        " order by count(ct) desc", GroupByDto.class)
                .getResultList();
    }

    public List<GroupByDto> getInterviewStatics(){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(i.interviewCategory,count(i)) " +
                        " from Interview i" +
                        " group by i.interviewCategory " +
                        " order by count(i) desc", GroupByDto.class)
                .getResultList();
    }
    public   List<GroupByDto> getMyApplyStatus(User user){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(j.resultStatus,count(j))" +
                        " from JobEvent j " +
                        " where j.user=:user" +
                        " group by j.resultStatus" +
                        " order by count(j) desc", GroupByDto.class)
                .setParameter("user", user)
                .getResultList();
    }

}
