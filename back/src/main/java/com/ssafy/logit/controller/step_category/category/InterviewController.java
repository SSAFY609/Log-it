package com.ssafy.logit.controller.step_category.category;


import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.step_category.dto.category.interview.CreateInterviewRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.CreateUpdateInterviewList;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewResponse;
import com.ssafy.logit.model.step_category.dto.category.interview.UpdateInterviewRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.InterviewService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/step-category/interview")
@RequiredArgsConstructor
@Tag(name = "interview API", description = "채용전형(면접) API")
public class InterviewController {

    private final InterviewService interviewService;
    private final UserService userService;
    private final StepCategoryService stepCategoryService;

    @Operation(summary = "면접 생성", description = "request을 이용하여 면접을 생성합니다.")
    @PostMapping
    public ResponseEntity<InterviewResponse> create(@RequestAttribute String email,
                                                    @Validated @RequestBody CreateInterviewRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(request.getStepId());
        Interview interview = interviewService.create(user, stepCategory, request);
        InterviewResponse response = new InterviewResponse(interview);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @Operation(summary = "면접 조회", description = "id값을 이용하여 면접을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> get(@PathVariable Long id) {
        Interview interview = interviewService.get(id);
        InterviewResponse response = new InterviewResponse(interview);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "면접 수정", description = "면접id 값을 이용하여 면접 수정 ")
    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponse> update(@RequestAttribute String email,
                                                    @PathVariable Long id,
                                                    @Validated @RequestBody UpdateInterviewRequest request) {
        User user = getUser(email);
        Interview updateInterview = interviewService.update(user, id, request);
        InterviewResponse interviewResponse = new InterviewResponse(updateInterview);
        return new ResponseEntity<>(interviewResponse, HttpStatus.OK);
    }


    @Operation(summary = "면접 삭제", description = "id값을 이용하여 면접을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id) {
        User user = getUser(email);
        interviewService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "생성,수정 전부 처리", description = "request에 interview 객체 리스트를 담아 생성, 수정")
    @PostMapping("/create-update-all")
    public ResponseEntity<ResultDto> createUpdateAll(@RequestAttribute String email,
                                                     @Validated @RequestBody CreateUpdateInterviewList interviews) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(interviews.getStepId());
        interviewService.createUpdateAll(user, stepCategory, interviews.getList());

        //  stepEtc 리스트
        List<InterviewResponse> results = stepCategory
                .getInterviewList()
                .stream()
                .map(o -> new InterviewResponse(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResultDto(results.size(),results),HttpStatus.CREATED);
    }


    private User getUser(String email) {
        return userService.getUserEntity(email);
    }


}
