package com.ssafy.logit.model.statistics.repository;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository {
    private final EntityManager em;


//    @Query("select " +
//            " new com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics(st.resultStatus,count(ct) ) " +
//            " from StepCategory st" +
//            " group by st.resultStatus" +
//            " order by count(st) desc")
//    List<AlgoCategoryStatistics> getGroupByAlgoCategory();

//    @Query("select " +
//            " new com.ssafy.logit.model.statistics.dto.GroupByDto(j.resultStatus.getValue(),count(j) ) " +
//            " from JobEvent j " +
//            " where j.user=:user" +
//            " group by j.resultStatus")
//    List<AlgoCategoryStatistics> GetMyApplyStatus(@Param("user")User user);

//    private List<>
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
