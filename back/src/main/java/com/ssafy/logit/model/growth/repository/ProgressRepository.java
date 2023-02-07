package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository  extends JpaRepository<Progress, Long> {
}
