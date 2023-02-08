package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.LikeProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeProgress, Long> {

    @Query(value = "select count(*) from like_progress where user_id = ?1 and progress_id = ?2", nativeQuery = true)
    public int cntMyLike(long userId, long progressId);

    @Query(value = "select * from like_progress where user_id = ?1 and progress_id = ?2", nativeQuery = true)
    public Optional<LikeProgress> findMyLike(long userId, long progressId);

    @Query(value = "select * from progress where growth_id = ?1 and progress_id in (select progress_id from like_progress where user_id = ?2)", nativeQuery = true)
    public Optional<List<Long>> getLikeProgress(long growthId, long userId);
}
