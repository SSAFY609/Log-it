package com.ssafy.logit.model.statistics.service;

import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEventStatus;
import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.statistics.dto.PassPercentageDto;
import com.ssafy.logit.model.statistics.repository.StatisticsRepository;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final StepCategoryRepository stepCategoryRepository;

    public List<GroupByDto> getInterviewStatistics() {
        HashSet<InterviewCategory> hs = new HashSet<>();

        // 현재 면접 유형 리스트
        for (InterviewCategory category : InterviewCategory.values()) {
            hs.add(category);
        }
        List<GroupByDto> interviewStatics = statisticsRepository.getInterviewStatics();

        // 있는 것 제외
        for (GroupByDto dto : interviewStatics) {
            hs.remove(InterviewCategory.nameOf(dto.getName()));
        }
        // 없는 것 0으로 처리
        for (InterviewCategory category : hs) {
            interviewStatics.add(new GroupByDto(category, 0L));
        }
        return interviewStatics;
    }

    public List<GroupByDto> getAlgoStatistics() {

        HashSet<AlgoCategory> hs = new HashSet<>();
        // 현재 알고리즘 유형 리스트
        for (AlgoCategory category : AlgoCategory.values()) {
            hs.add(category);
        }
        List<GroupByDto> algoStatics = statisticsRepository.getAlgoStatics();

        // 있는 것 제외
        for (GroupByDto dto : algoStatics) {
            hs.remove(AlgoCategory.nameOf(dto.getName()));
        }
        // 없는 것 0으로 처리
        for (AlgoCategory category : hs) {
            algoStatics.add(new GroupByDto(category, 0L));
        }
        return algoStatics;
    }

    public List<GroupByDto> getMyApplyStatus(User user) {
        long tot = 0;
        List<GroupByDto> groupByDtos = statisticsRepository.getMyApplyStatus(user);

        HashSet<JobEventStatus> hs = new HashSet<>();

        // JobEventStatus 리스트
        for (JobEventStatus status : JobEventStatus.values()) {
            hs.add(status);
        }
        // 존재하는 것 빼기
        for (GroupByDto dto : groupByDtos) {
            hs.remove(JobEventStatus.nameOf(dto.getName()));
        }
        // 없는 것 0으로 처리
        for (JobEventStatus status : hs) {
            groupByDtos.add(new GroupByDto(status, 0L));
        }

        for (GroupByDto dto : groupByDtos) {
            tot += dto.getCount();
        }
        groupByDtos.add(new GroupByDto("총합", tot));
        return groupByDtos;
    }


    public Map<String, List<GroupByDto>> getCompanyStatics(String companyName) {
        Map<String, List<GroupByDto>> results = new ConcurrentHashMap<>();
        List<GroupByDto> documentResult = statisticsRepository.companyTypeResult(companyName, JobCategory.DOCUMENT);
        List<GroupByDto> codingResult = statisticsRepository.companyTypeResult(companyName, JobCategory.CODINGTEST);
        List<GroupByDto> interviewResult = statisticsRepository.companyTypeResult(companyName, JobCategory.INTERVIEW);

        results.put("document", documentResult);
        results.put("codingtest", codingResult);
        results.put("interview", interviewResult);
        return results;
    }

    /**
     * 해당 유저의 유형별 합격비율 전체 반환
     * @param user
     * @return
     */
    public Map<String, Object> getMyTypeResults(User user) {
        Map<String, PassPercentageDto> jobTypeData = new ConcurrentHashMap<>();
        jobTypeData.put("document", getPasspercentage(user, JobCategory.DOCUMENT));
        jobTypeData.put("codingtest", getPasspercentage(user, JobCategory.CODINGTEST));
        jobTypeData.put("interview", getPasspercentage(user, JobCategory.INTERVIEW));

        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("data", jobTypeData);
        result.put("name", "myJobType");

        return result;
    }

    /**
     * 유저의 해당 전형(서류, 코테, 면접)의 합격비율 반환
     *
     * @param user
     * @param jobCategory
     * @return
     */
    public PassPercentageDto getPasspercentage(User user, JobCategory jobCategory) {
        long pass = stepCategoryRepository.countByUserCategoryAndStatus(user, ResultStatus.PASS, jobCategory);
        long tot = stepCategoryRepository.countByUserCategory(user, jobCategory);
        double percentage = Math.round((1.0 * pass * 1000) / tot) / 10.0; // 소숫점 하나
        return new PassPercentageDto(pass, tot, percentage);
    }

    public List<GroupByDto> getCompanyLank() {
        return statisticsRepository.companyRankList();
    }


}
