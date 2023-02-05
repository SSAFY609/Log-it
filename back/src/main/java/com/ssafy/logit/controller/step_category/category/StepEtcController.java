package com.ssafy.logit.controller.step_category.category;

import com.ssafy.logit.model.step_category.dto.category.etc.CreateStepEtcRequest;
import com.ssafy.logit.model.step_category.dto.category.etc.StepEtcResponse;
import com.ssafy.logit.model.step_category.dto.category.etc.UpdateStepEtcRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.StepEtcService;
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
@RequestMapping("/step-category/etc")
@RequiredArgsConstructor
@Tag(name = "step etc API", description = "채용전형(기타) API")
public class StepEtcController {
    private final StepEtcService stepEtcService;
    private final UserService userService;
    private final StepCategoryService stepCategoryService;


    @PostMapping
    public ResponseEntity<StepEtcResponse> create(@RequestAttribute String email,
                                                  @Validated @RequestBody CreateStepEtcRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(request.getStepId());
        System.out.println("stepCategory.getId() = " + stepCategory.getId());
        StepEtc stepEtc = stepEtcService.create(user, stepCategory, request);
        StepEtcResponse response = new StepEtcResponse(stepEtc);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepEtcResponse> get(@PathVariable Long id) {
        StepEtc stepEtc = stepEtcService.get(id);
        StepEtcResponse response = new StepEtcResponse(stepEtc);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StepEtcResponse> update(@RequestAttribute String email,
                                                    @PathVariable Long id,
                                                    @Validated @RequestBody UpdateStepEtcRequest request) {
        User user = getUser(email);
        StepEtc updateStepEtc = stepEtcService.update(user, id, request);
        StepEtcResponse stepEtcResponse = new StepEtcResponse(updateStepEtc);
        return new ResponseEntity<>(stepEtcResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id) {
        User user = getUser(email);
        stepEtcService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }
    
}
