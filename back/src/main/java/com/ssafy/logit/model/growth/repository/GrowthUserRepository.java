package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.GrowthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthUserRepository extends JpaRepository<GrowthUser, Long> {

    @Query(value = "select * from growth_user where user_id = ?1 and type = true", nativeQuery = true)
    public List<GrowthUser> findMyEvent(long userId);
}