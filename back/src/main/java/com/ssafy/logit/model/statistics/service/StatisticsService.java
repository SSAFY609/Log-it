package com.ssafy.logit.model.statistics.service;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.statistics.repository.StatisticsRepository;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        long tot = 0;
        List<GroupByDto> groupByDtos = statisticsRepository.getMyApplyStatus(user);
        for(GroupByDto dto: groupByDtos){
            tot += dto.getCount();
        }
        groupByDtos.add(new GroupByDto("총합", tot));
        return groupByDtos;
    }


    public Map<String,List<GroupByDto>> getCompanyStatics(String companyName){
        Map<String,List<GroupByDto>> results = new ConcurrentHashMap<>();
        List<GroupByDto> documentResult = statisticsRepository.companyTypeResult(companyName, JobCategory.DOCUMENT);
        List<GroupByDto> codingResult = statisticsRepository.companyTypeResult(companyName, JobCategory.CODINGTEST);
        List<GroupByDto> interviewResult = statisticsRepository.companyTypeResult(companyName, JobCategory.INTERVIEW);

        results.put("document", documentResult);
        results.put("codingtest", codingResult);
        results.put("interview", interviewResult);
        return results;
    }

    public Map<String,List<GroupByDto>> getMyTypeResults(User user){
        Map<String, List<GroupByDto>> results = new ConcurrentHashMap<>();
        List<GroupByDto> documentResult = statisticsRepository.myTypeResult(user, JobCategory.DOCUMENT);
        List<GroupByDto> codingResult = statisticsRepository.myTypeResult(user, JobCategory.CODINGTEST);
        List<GroupByDto> interviewResult = statisticsRepository.myTypeResult(user, JobCategory.INTERVIEW);
        results.put("document", documentResult);
        results.put("codingtest", codingResult);
        results.put("interview", interviewResult);
        return results;


    }
    public List<GroupByDto> getCompanyLank(){

        return statisticsRepository.companyRankList();

    }




}
