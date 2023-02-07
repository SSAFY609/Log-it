package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Growth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Long> {

    public List<Growth> findByUserId(long userId);
}