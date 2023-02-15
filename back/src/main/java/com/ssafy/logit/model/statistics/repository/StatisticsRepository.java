package com.ssafy.logit.model.statistics.repository;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository {
    private final EntityManager em;


    /**
     * 알고리즘 전형 통계
     * @return
     */
    public List<GroupByDto> getAlgoStatics(){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(ct.algoCategory,count(ct))" +
                        " from CodingTest ct " +
                        " group by ct.algoCategory", GroupByDto.class)
                .getResultList();
    }

    /**
     * 면접 전형 통계
     * @return
     */
    public List<GroupByDto> getInterviewStatics(){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(i.interviewCategory,count(i)) " +
                        " from Interview i" +
                        " group by i.interviewCategory ", GroupByDto.class)
                .getResultList();
    }

    /**
     * 내 지원 통계
     * @param user
     * @return
     */
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


    /**
     * 회사별 전형 합격 통계
     * @param companyName
     * @param jobCategory
     * @return
     */
    public List<GroupByDto> companyTypeResult(String companyName, JobCategory jobCategory){
        List<GroupByDto> companyCodingResult = em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(j.resultStatus,count(j))" +
                        " from JobEvent j" +
                        " join j.stepCategoryList sc " +
                        " where sc.jobCategory = :jobCategory " +
                        " and j.companyName=:companyName" +
                        " group by sc.resultStatus", GroupByDto.class)
                .setParameter("companyName", companyName)
                .setParameter("jobCategory",jobCategory)
                .getResultList();
        return companyCodingResult;
    }
    /**
     * 지원한 회사 순위
     * @return
     */
    public List<GroupByDto> companyRankList(){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(j.companyName,count(j))" +
                        " from JobEvent j" +
                        " group by j.companyName " +
                        " order by count(j) desc", GroupByDto.class)
                .getResultList();
    }


    /**
     * 내 전형별 합격 비율
     * @param user
     * @param jobCategory
     * @return
     */
    public List<GroupByDto> myTypeResult(User user,JobCategory jobCategory){
        return em.createQuery("" +
                        " select new com.ssafy.logit.model.statistics.dto.GroupByDto(sc.resultStatus,count(sc))" +
                        " from StepCategory sc" +
                        " join sc.jobEvent j" +
                        " where sc.jobCategory = :jobCategory" +
                        " and j.user = :user" +
                        " group by sc.resultStatus ", GroupByDto.class)
                .setParameter("user", user)
                .setParameter("jobCategory", jobCategory)
                .getResultList();
    }

}
