package com.ssafy.logit.controller.step_category.category;


import com.ssafy.logit.model.step_category.dto.category.interview.CreateInterviewRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewResponse;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.InterviewService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/step-category/interivew")
@RequiredArgsConstructor
@Tag(name = "interview API", description = "채용전형(면접) API")
public class InterviewController {

    private final InterviewService interviewService;
    private final UserService userService;
    private final StepCategoryService stepCategoryService;


    @PostMapping
    public ResponseEntity<InterviewResponse> create(@RequestAttribute String email,
                                                    @Validated @RequestBody CreateInterviewRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(request.getStepId());
        Interview interview = interviewService.create(user, stepCategory, request);
        InterviewResponse response = new InterviewResponse(interview);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponse> get(@PathVariable Long id) {
        Interview interview = interviewService.get(id);
        InterviewResponse response = new InterviewResponse(interview);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponse> update(@RequestAttribute String email,
                                                    @PathVariable Long id,
                                                    @Validated @RequestBody CreateInterviewRequest request) {
        User user = getUser(email);
        Interview updateInterview = interviewService.update(user, id, request);
        InterviewResponse interviewResponse = new InterviewResponse(updateInterview);
        return new ResponseEntity<>(interviewResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id) {
        User user = getUser(email);
        interviewService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }


}
