package com.ssafy.logit.controller.job;

import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.dto.UpdateJobEventRequest;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.service.JobService;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/job")
@Tag(name="job",description = "취업 여정 이벤트 API")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final UserService userService;


    @Operation(summary = "취업 이벤트 생성", description = "취업 이벤트를 생성")
    @PostMapping
    public ResponseEntity<CreateJobEventResponse> create(@RequestAttribute String email, @RequestBody @Validated  CreateJobEventRequest request){
        User user = userService.getUser(email).toEntity();
        JobEvent jobEvent = jobService.create(user, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "취업 이벤트 모두 조회", description = "해당 유저의 취업 이벤트를 모두 조회")
    @GetMapping
    public ResponseEntity<ResultDto> getAll(@RequestAttribute String email){
        User user = userService.getUser(email).toEntity();
        List<JobEvent> events = jobService.getEvents(user);

        List<CreateJobEventResponse> collect = events.stream()
                .map(o -> new CreateJobEventResponse(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResultDto(collect.size(),collect), HttpStatus.OK);
    }


    @Operation(summary = "취업 이벤트 수정", description = "id값을 이용하여 취업 이벤트 수정합니다")
    @PutMapping("/{id}")
    public ResponseEntity<CreateJobEventResponse> put(@RequestAttribute String email,@PathVariable Long id,@RequestBody  @Validated UpdateJobEventRequest request){
        User user = userService.getUser(email).toEntity();
        JobEvent jobEvent = jobService.update(user,id, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.OK);
    }


    @Operation(summary = "취업 이벤트 삭제", description = "id값을 이용하여 취업 이벤트 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@RequestAttribute String email,@PathVariable Long id, @RequestBody  @Validated CreateJobEventRequest request){
        User user = userService.getUser(email).toEntity();
        jobService.delete(user,id);
        Map<String,String>  hm = new ConcurrentHashMap<>();
        hm.put("message","deleteSuccess");
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }



}
