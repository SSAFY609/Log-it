package com.ssafy.logit.model.statistics.service;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.statistics.repository.StatisticsRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public List<GroupByDto> getInterviewStatistics(){
        return statisticsRepository.getInterviewStatics();
    }

    public List<GroupByDto> getAlgoStatistics(){
        return statisticsRepository.getAlgoStatics();
    }
    public List<GroupByDto> getMyApplyStatus(User user){
        return statisticsRepository.getMyApplyStatus(user);
    }
}
