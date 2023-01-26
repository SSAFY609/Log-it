package com.ssafy.logit.controller.job;

import com.ssafy.logit.model.job.dto.CreateJobEventRequest;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/job")
@Tag(name="job",description = "취업 여정 이벤트 API")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @Operation(summary = "취업 이벤트 생성", description = "취업 이벤트를 생성")
    @PostMapping
    public ResponseEntity<CreateJobEventResponse> create(@RequestBody CreateJobEventRequest request){
         // todo token -> userId
        JobEvent jobEvent = jobService.create(1L, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "취업 이벤트 모두 조회", description = "해당 유저의 취업 이벤트를 모두 조회")
    @GetMapping
    public ResponseEntity<CreateJobEventResponse> getAll(){
        // todo token -> userId
        List<JobEvent> events = jobService.getEvents(1L);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @Operation(summary = "취업 이벤트 수정", description = "id값을 이용하여 취업 이벤트 수정합니다")
    @PutMapping("/{id}")
    public ResponseEntity<CreateJobEventResponse> put(@PathVariable Long id,@RequestBody CreateJobEventRequest request){
        // todo token -> id
        JobEvent jobEvent = jobService.update(1L,id, request);
        CreateJobEventResponse createJobEventResponse = new CreateJobEventResponse(jobEvent);
        return new ResponseEntity<>(createJobEventResponse, HttpStatus.OK);
    }

    @Operation(summary = "취업 이벤트 삭제", description = "id값을 이용하여 취업 이벤트 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id, @RequestBody CreateJobEventRequest request){
        // todo token -> id
        jobService.delete(1L,id);
        Map<String,String>  hm = new ConcurrentHashMap<>();
        hm.put("message","deleteSuccess");
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }
}
