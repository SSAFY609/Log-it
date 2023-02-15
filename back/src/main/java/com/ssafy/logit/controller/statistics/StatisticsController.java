package com.ssafy.logit.controller.statistics;

import com.ssafy.logit.model.statistics.dto.GroupByDto;
import com.ssafy.logit.model.statistics.dto.ResultStatisticsDto;
import com.ssafy.logit.model.statistics.service.StatisticsService;
import com.ssafy.logit.model.step_category.service.category.CodingTestService;
import com.ssafy.logit.model.step_category.service.category.InterviewService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@Tag(name = "통계 API",description = "데이터로부터 다양한 통계를 반환하는 api")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {
    private final CodingTestService codingTestService;
    private final InterviewService interviewService;
    private final UserService userService;
    private final StatisticsService statisticsService;


    @GetMapping
    @Operation(summary = "모든 통계 확인",description = "모든 통계 데이터를 확인합니다.")
    public ResponseEntity<List<ResultStatisticsDto>> getAll(@RequestAttribute String email){
        User user = userService.getUserEntity(email);
        List<ResultStatisticsDto> results = new ArrayList<>();
        results.add(new ResultStatisticsDto("algorithm", statisticsService.getAlgoStatistics()));
        results.add(new ResultStatisticsDto("interview", statisticsService.getInterviewStatistics()));
        results.add(new ResultStatisticsDto("myApply", statisticsService.getMyApplyStatus(user)));
        results.add(new ResultStatisticsDto("companyRank", statisticsService.getCompanyLank()));
        return new ResponseEntity<>(results, HttpStatus.OK);
    }


    @GetMapping("/algorithm")
    @Operation(summary = "알고리즘 통계",description = "전체 사용자 알고리즘 통계")
    public ResponseEntity<List<GroupByDto>> getMyApplyStatistics(){
        List<GroupByDto> algoStatistics = statisticsService.getAlgoStatistics();
        return new ResponseEntity<>(algoStatistics, HttpStatus.OK);
    }

    @GetMapping("/interview")
    @Operation(summary = "면접 통계",description = "전체 사용자 면접유형 통계")
    public  ResponseEntity<List<GroupByDto>> GetInterivewStatics(){
        List<GroupByDto> interviewStatistics = statisticsService.getInterviewStatistics();
        return new ResponseEntity<>(interviewStatistics, HttpStatus.OK);
    }
    
    
    @GetMapping("/random-interview")
    @Operation(summary = "면접 질문 랜덤 10개",description = "면접 질문을 랜덤으로 10개 반환")
    public ResponseEntity<List<String>> getRandomInterview(){
        List<String> randomInterviews = interviewService.getRandomInterviews();
        return new ResponseEntity<>(randomInterviews, HttpStatus.OK);
    }

    @GetMapping("/company/{companyName}")
    @Operation(summary = "회사의 각 전형별 합격률",description = "회사의 서류, 면접, 코테의 합격률을 보여줌")
    public ResponseEntity<Map<String,List<GroupByDto>>> getAllTest(@PathVariable String companyName){
        Map<String, List<GroupByDto>> companyStatics = statisticsService.getCompanyStatics(companyName);
        return new ResponseEntity<>(companyStatics, HttpStatus.OK);
    }

    @GetMapping("rank/company")
    @Operation(summary = "지원한 회사 순위 ",description = "싸피생이 지원한 회사 순위")
    public ResponseEntity<List<GroupByDto>> getCompanyRank(){
        List<GroupByDto> companyLank = statisticsService.getCompanyLank();
        return new ResponseEntity<>(companyLank, HttpStatus.OK);
    }

    @GetMapping("/my/apply")
    @Operation(summary = "내 지원 현황",description = "내 지원 현황 통계")
    public ResponseEntity<List<GroupByDto>> getMyApplyStatistics(@RequestAttribute String email){
        User user = userService.getUserEntity(email);
        List<GroupByDto> myApplyStatus = statisticsService.getMyApplyStatus(user);
        return new ResponseEntity<>(myApplyStatus, HttpStatus.OK);
    }


    @GetMapping("my/job-type")
    @Operation(summary = "내 지원별 합격률",description = "내 지원별 합격률 통계")
    public ResponseEntity<Map<String,List<GroupByDto>>> getMyTypeResult(@RequestAttribute String email){
        User user = userService.getUserEntity(email);
        Map<String, List<GroupByDto>> myTypeResults = statisticsService.getMyTypeResults(user);
        return new ResponseEntity<>(myTypeResults, HttpStatus.OK);
    }

}
