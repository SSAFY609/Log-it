package com.ssafy.logit.controller.statistics;

import com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics;
import com.ssafy.logit.model.step_category.service.category.CodingTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@Tag(name = "통계 API",description = "데이터로부터 다양한 통계를 반환하는 api")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {
    private final CodingTestService codingTestService;



    @GetMapping("/algorithm")
    @Operation(summary = "통계",description = "사용자 알고리즘 통계")
    public ResponseEntity<List<AlgoCategoryStatistics>> getAlgoStatics(){
        log.info("GOOD");

        List<AlgoCategoryStatistics> algoStatics = codingTestService.getAlgoStatics();
        return new ResponseEntity<>(algoStatics, HttpStatus.OK);
    }




}
