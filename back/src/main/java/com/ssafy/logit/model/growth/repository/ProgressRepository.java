package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository  extends JpaRepository<Progress, Long> {

    @Query(value = "select date from progress group by date", nativeQuery = true)
    public Optional<List<String>> dateList();

    @Query(value = "select * from progress where date = ?1", nativeQuery = true)
    public Optional<List<Progress>> findByDate(String date);
}