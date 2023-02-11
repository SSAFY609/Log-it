package com.ssafy.logit.controller.job;

import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.job.dto.CreateJobEventCategoryRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.job.service.JobService;
import com.ssafy.logit.model.step_category.dto.StepCategoryDto;
import com.ssafy.logit.model.step_category.dto.category.StepCategoryResultDto;
import com.ssafy.logit.model.step_category.dto.category.entire.JobEventAllRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.CodingTestService;
import com.ssafy.logit.model.step_category.service.category.DocumentService;
import com.ssafy.logit.model.step_category.service.category.InterviewService;
import com.ssafy.logit.model.step_category.service.category.StepEtcService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/job")
@Tag(name = "job", description = "취업 여정 이벤트 API")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final StepCategoryService stepCategoryService;
    private final UserService userService;
    private final InterviewService interviewService;
    private final CodingTestService codingTestService;
    private final StepEtcService stepEtcService;
    private final DocumentService documentService;
    private final JobRepository jobRepository;


    @Operation(summary = "취업 이벤트 생성", description = "취업 이벤트를 생성합니다. 채용전형이 있는 경우 같이 생성")
    @PostMapping
    public ResponseEntity<CreateJobEventResponse> create(@RequestAttribute String email, @RequestBody @Validated CreateJobEventCategoryRequest request) {
        User user = getUser(email);
        JobEvent jobEvent = jobService.createAll(user, request);

        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "취업 이벤트 순수 생성", description = "취업 이벤트를 생성")
    @PostMapping("/basic-create")
    public ResponseEntity<CreateJobEventResponse> basicCreate(@RequestAttribute String email, @RequestBody @Validated CreateJobEventRequest request) {
        User user = getUser(email);
        JobEvent jobEvent = jobService.create(user, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "취업 이벤트 모두 조회", description = "해당 유저의 취업 이벤트를 모두 조회")
    @GetMapping
    public ResponseEntity<ResultDto> getAll(@RequestAttribute String email) {
        User user = getUser(email);
        List<JobEvent> events = jobService.getEvents(user);

        List<CreateJobEventResponse> collect = events.stream()
                .map(o -> new CreateJobEventResponse(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResultDto(collect.size(), collect), HttpStatus.OK);
    }

    @Operation(summary = "취업 이벤트 수정", description = "id값을 이용하여 취업 이벤트 수정합니다")
    @PutMapping("/{id}")
    public ResponseEntity<CreateJobEventResponse> put(@RequestAttribute String email, @PathVariable Long id, @RequestBody @Validated UpdateJobEventRequest request) {
        User user = getUser(email);
        JobEvent jobEvent = jobService.update(user, id, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.OK);
    }

    @Operation(summary = "jobId의 모든 정보 조회",description = "지원한 기업의 모든 정보를 조회")
    @GetMapping("/{id}")
    public ResponseEntity<StepCategoryResultDto> test(@RequestAttribute String email, @PathVariable Long id) {
        User user = getUser(email);
        JobEvent jobEvent = jobRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<StepCategory> stepCategories = stepCategoryService.findStepCategories(jobEvent);

        List<Object> stepList = stepCategories.stream()
                .map(o -> stepCategoryService.getStepCategory(o))
                .collect(Collectors.toList());
        List<StepCategoryDto> results = new ArrayList<>();

        for (int i = 0; i < stepCategories.size(); i++) {
            results.add(new StepCategoryDto(stepCategories.get(i), stepList.get(i)));
        }
        return new ResponseEntity<>(new StepCategoryResultDto(
                jobEvent.getId(),
                jobEvent.getStartDate(),
                jobEvent.getStartDate(),
                results.size(),
                results), HttpStatus.OK);
    }


    @Operation(summary = "취업 이벤트 삭제", description = "id값을 이용하여 취업 이벤트 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@RequestAttribute String email, @PathVariable Long id, @RequestBody @Validated CreateJobEventRequest request) {
        User user = getUser(email);
        jobService.delete(user, id);
        Map<String, String> hm = new ConcurrentHashMap<>();
        hm.put("message", "deleteSuccess");
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    @Operation(summary = "취업 이벤트 시작 기준 정렬", description = "유저의 취업 이벤트를 시작날짜를 기준으로 정렬합니다.")
    @GetMapping("/sort")
    public ResponseEntity<ResultDto> sortByStartDate(@RequestAttribute String email) {
        User user = getUser(email);
        List<JobEvent> events = jobService.getAllByStartDate(user);
        List<CreateJobEventResponse> collect = events.stream()
                .map(o -> new CreateJobEventResponse(o))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ResultDto(collect.size(), collect), HttpStatus.OK);
    }


    @Operation(summary = "취업 이벤트 생성 ", description = "취업이벤트 모두 생성 합니다.")
    @PostMapping("/post-all")
    public ResponseEntity<Void> postAll(@RequestAttribute String email, @RequestBody JobEventAllRequest request) {
        User user = getUser(email);
        jobService.postAll(user,request);
        return new ResponseEntity(HttpStatus.OK);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }


}
