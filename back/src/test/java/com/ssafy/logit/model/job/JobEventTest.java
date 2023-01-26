package com.ssafy.logit.model.job;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobEventTest {


    @PersistenceContext
    EntityManager em;


    @Test
    @Transactional
    public void  testEntity() throws Exception{
        //given
//        User user = new User();
//        em.persist(user);
//        LocalDate now = LocalDate.now();
//        LocalDate after = now.plusDays(5);

        JobEvent jobEvent1 = JobEvent.createJobEvent(null, "company1", "인턴", false, null);
        JobEvent jobEvent2 = JobEvent.createJobEvent(null, "company2", "신입", false, null);

        em.persist(jobEvent1);
        em.persist(jobEvent2);
        //when
        List<JobEvent> resultList = em.createQuery("select j from JobEvent  j", JobEvent.class).getResultList();

        for (JobEvent jobEvent : resultList) {
            System.out.println("jobEvent = " + jobEvent.getCompanyName());
        }


//        JobEvent findEvent1 = em.createQuery("select j from JobEvent j where j.companyName=:companyName", JobEvent.class)
//                .setParameter("companyName","인턴")
//                .getSingleResult();

        //then
//        Assertions.assertThat(jobEvent1).isEqualTo(findEvent1);

    }

}