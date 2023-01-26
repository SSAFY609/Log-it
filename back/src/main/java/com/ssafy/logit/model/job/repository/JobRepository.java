package com.ssafy.logit.model.job.repository;

import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  JobRepository extends JpaRepository<JobEvent,Long> {

    List<JobEvent> findAllByUser(User user);
}

