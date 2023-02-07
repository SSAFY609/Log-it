package com.ssafy.logit.controller.step_category;


import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.repository.JobRepository;
import com.ssafy.logit.model.step_category.dto.CreateStepCategoryRequest;
import com.ssafy.logit.model.step_category.dto.CreateStepCategoryResponse;
import com.ssafy.logit.model.step_category.dto.StepCategoryDto;
import com.ssafy.logit.model.step_category.dto.UpdateStepCategoryRequest;
import com.ssafy.logit.model.step_category.dto.category.StepCategoryResultDto;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/step-category")
@Tag(name = "step-category", description = "채용전형분류 API")
@Slf4j
public class StepCategoryController {
    private final StepCategoryService stepCategoryService;
    private final UserService userService;

    private final JobRepository jobRepository;


    @Operation(summary = "채용 전형 생성 ", description = "채용 전형 데이터를 생성합니다. RequestBody에 취업이벤트의 id가 필요합니다.")
    @PostMapping
    public ResponseEntity<CreateStepCategoryResponse> create(@RequestAttribute String email, @RequestBody @Validated  CreateStepCategoryRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.create(user, request);
        CreateStepCategoryResponse response = new CreateStepCategoryResponse(stepCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "단일 채용 전형 조회 ", description = "id값을 이용하여 단일 채용 전형 데이터를 조회합니다")
    @GetMapping("/{id}")
    public ResponseEntity<CreateStepCategoryResponse> get(@PathVariable Long id) {
        StepCategory stepCategory = stepCategoryService.get(id);
        CreateStepCategoryResponse response = new CreateStepCategoryResponse(stepCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "채용 전형 수정 ", description = "id값을 이용하여 단일 채용 전형 데이터를 수정합니다. 취업여정 이벤트의 유저와 동일해야 합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<CreateStepCategoryResponse> update(
            @RequestAttribute String email
            , @PathVariable Long id, @RequestBody @Validated UpdateStepCategoryRequest request) {
        log.info("채용 전형 수정 Controller {} ",request);
        User user = getUser(email);
        StepCategory updateCategory = stepCategoryService.update(user, id, request);
        log.info("수정 : {} ", updateCategory);
        CreateStepCategoryResponse response = new CreateStepCategoryResponse(updateCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "채용 전형 삭제 ", description = "id값을 이용하여 단일 채용 전형 데이터를 삭제합니다. 취업여정 이벤트의 유저와 동일해야 합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @RequestAttribute String email, @PathVariable Long id) {
        User user = getUser(email);
        stepCategoryService.delete(user, id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    @GetMapping("/test/{jobEventId}")
    public ResponseEntity<StepCategoryResultDto> test(@RequestAttribute String email, @PathVariable Long jobEventId){
        User user = getUser(email);
        JobEvent jobEvent = jobRepository.findById(jobEventId).get();
        List<StepCategory> stepCategories = stepCategoryService.findStepCategories(jobEvent);
        List<Object> stepList = stepCategories.stream()
                .map(o -> stepCategoryService.getStepCategory(o))
                .collect(Collectors.toList());
        List<StepCategoryDto> results = new ArrayList<>();

        for(int i = 0 ; i<stepCategories.size();i++){
            results.add(new StepCategoryDto(stepCategories.get(i), stepList.get(i)));
        }
        return new ResponseEntity<>(new StepCategoryResultDto(
                jobEvent.getStartDate(),
                jobEvent.getStartDate(),
                results.size(),
                results), HttpStatus.OK);
    }



    private User getUser(String email) {
        return userService.getUserEntity(email);
    }
    
    
}
