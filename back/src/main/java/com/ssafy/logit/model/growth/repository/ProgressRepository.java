package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository  extends JpaRepository<Progress, Long> {

    @Query(value = "select date from progress where growth_id = ?1 group by date order by date", nativeQuery = true)
    public Optional<List<String>> dateList(long growthId);

    @Query(value = "select * from progress where date = ?1", nativeQuery = true)
    public Optional<List<Progress>> findByDate(String date);

    @Query(value = "select * from progress where growth_id = ?1 date = ?2 and user_id = ?3", nativeQuery = true)
    public Optional<Progress> getMine(long growthId, String date, long userId);

    @Query(value = "select * from progress where growth_id = ?1 and date = ?2 order by like_cnt desc, progress_id asc limit 1", nativeQuery = true)
    public Optional<Progress> getFirst(long growthId, String date);

    @Query(value = "select * from progress where growth_id = ?1 and date = ?2 and user_id != ?3 order by like_cnt desc, progress_id asc", nativeQuery = true)
    public Optional<List<Progress>> getDateProgress(long growth_id, String date, long user_id);

    @Query(value = "select * from progress where user_id = ?1 and content like %?2%", nativeQuery = true)
    public Optional<List<Progress>> search(long userId, String keyword);
}