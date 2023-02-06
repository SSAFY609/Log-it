package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Growth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Long> {
}