package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Growth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrowthRepository extends JpaRepository<Growth, Long> {

    public List<Growth> findByUserId(long userId);

    @Query(value = "select * from growth where (growth_id in (select growth_id from growth_user where user_id = ?1) or user_id = ?1) and category like %?2%", nativeQuery = true)
    public Optional<List<Growth>> search(long userId, String keyword);

    @Query(value = "select count(*) from growth where (growth_id in (select growth_id from growth_user where user_id = ?1) or user_id = ?1)", nativeQuery = true)
    public int checkEvent(long userId);
}