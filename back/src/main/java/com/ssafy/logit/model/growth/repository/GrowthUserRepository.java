package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.GrowthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrowthUserRepository extends JpaRepository<GrowthUser, Long> {

    public Optional<GrowthUser> findByUserId(long userId);
}