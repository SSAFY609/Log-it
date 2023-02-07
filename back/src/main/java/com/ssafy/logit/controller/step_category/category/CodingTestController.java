package com.ssafy.logit.controller.step_category.category;

import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CodingTestResponse;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CreateCodingTestRequest;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CreateUpdateCodingTestList;
import com.ssafy.logit.model.step_category.dto.category.codingtest.UpdateCodingTestRequest;
import com.ssafy.logit.model.step_category.dto.category.interview.CreateUpdateInterviewList;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewResponse;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.CodingTestService;
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
@RequiredArgsConstructor
@RequestMapping("/step-category/coding-test")
@Tag(name = "coding-test", description = "전형(코테) API")
@Slf4j
public class CodingTestController {
    private final UserService userService;
    private final CodingTestService codingTestService;
    private final StepCategoryService stepCategoryService;


    @Operation(summary = "코테 전형 생성 ", description = "코테 전형 데이터를 생성합니다. RequestBody에 취업이벤트의 id가 필요합니다.")
    @PostMapping
    public ResponseEntity<CodingTestResponse> create(@RequestAttribute String email, @RequestBody @Validated CreateCodingTestRequest request) {
        User user = getUser(email);
        CodingTest codingTest = codingTestService.create(user, request);
        CodingTestResponse response = new CodingTestResponse(codingTest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "단일 코테 전형 조회 ", description = "id값을 이용하여 단일 코테 전형 데이터를 조회합니다")
    @GetMapping("/{id}")
    public ResponseEntity<CodingTestResponse> get(@PathVariable Long id) {
        CodingTest codingTest = codingTestService.get(id);
        CodingTestResponse response = new CodingTestResponse(codingTest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "코테 전형 수정 ", description = "id값을 이용하여 단일 코테 전형 데이터를 수정합니다.  채용 전형의 유저와 동일해야 합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<CodingTestResponse> update(
            @RequestAttribute String email
            , @PathVariable Long id, @RequestBody @Validated UpdateCodingTestRequest request) {
        User user = getUser(email);
        CodingTest updateCategory = codingTestService.update(user, id, request);
        CodingTestResponse response = new CodingTestResponse(updateCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "코테 전형 삭제 ", description = "id값을 이용하여 단일 코테 전형 데이터를 삭제합니다. 채용 전형의 유저와 동일해야 합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @RequestAttribute String email, @PathVariable Long id) {
        User user = getUser(email);
        codingTestService.delete(user, id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @Operation(summary = "생성,수정 전부 처리", description = "request에 코딩테스트 객체 리스트를 담아 생성, 수정")
    @PostMapping("/create-update-all")
    public ResponseEntity<ResultDto> createUpdateAll(@RequestAttribute String email,
                                                     @Validated @RequestBody CreateUpdateCodingTestList codingTests) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(codingTests.getStepId());
        codingTestService.createUpdateAll(user, stepCategory, codingTests.getList());

        //  stepEtc 리스트
        List<CodingTestResponse> results = stepCategory
                .getCodingTestList()
                .stream()
                .map(o -> new CodingTestResponse(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResultDto(results.size(),results),HttpStatus.CREATED);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }
}
