package com.ssafy.logit.controller.step_category.category;


import com.ssafy.logit.model.step_category.dto.category.interview_detail.CreateInterviewDetailRequest;
import com.ssafy.logit.model.step_category.dto.category.interview_detail.InterviewDetailResponse;
import com.ssafy.logit.model.step_category.dto.category.interview_detail.UpdateInterviewDetailRequest;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.entity.category.InterviewDetail;
import com.ssafy.logit.model.step_category.service.category.InterviewDetailService;
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
@RequestMapping("/step-category/interview-detail")
@RequiredArgsConstructor
@Tag(name = "interview Detail API", description = "면접 상세 API")
public class InterviewDetailController {

    private final InterviewDetailService interviewDetailService;
    private final UserService userService;
    private final InterviewService interviewService;


    @PostMapping
    public ResponseEntity<InterviewDetailResponse> create(@RequestAttribute String email,
                                                    @Validated @RequestBody CreateInterviewDetailRequest request) {
        User user = getUser(email);
        System.out.println("request = " + request);
        Interview interview = interviewService.get(request.getInterviewId());
        InterviewDetail interviewDetail = interviewDetailService.create(user, interview, request);
        InterviewDetailResponse response = new InterviewDetailResponse(interviewDetail);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewDetailResponse> get(@PathVariable Long id) {
        InterviewDetail interviewDetail = interviewDetailService.get(id);
        InterviewDetailResponse response = new InterviewDetailResponse(interviewDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewDetailResponse> update(@RequestAttribute String email,
                                                    @PathVariable Long id,
                                                    @Validated @RequestBody UpdateInterviewDetailRequest request) {
        User user = getUser(email);
        InterviewDetail updateInterviewDetail = interviewDetailService.update(user, id, request);
        InterviewDetailResponse response = new InterviewDetailResponse(updateInterviewDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id) {
        User user = getUser(email);
        interviewDetailService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }


}
