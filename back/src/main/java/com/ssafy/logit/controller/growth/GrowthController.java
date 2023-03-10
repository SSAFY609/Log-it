package com.ssafy.logit.controller.growth;

import com.ssafy.logit.model.growth.dto.*;
import com.ssafy.logit.model.growth.service.GrowthService;
import com.ssafy.logit.model.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/growth")
@Tag(name="growth", description="성장 여정 API")
public class GrowthController {

    private static final String FAIL = "fail";

    @Autowired
    private GrowthService growthService;

    // 성장 이벤트 등록
    @Operation(summary = "성장 이벤트 등록", description = "성장 이벤트 등록 (카테고리, 공유할 회원, 날짜 선택 가능)")
    @PostMapping("/regist")
    public ResponseEntity<Long> registEvent(@RequestBody GrowthDto growthDto, @RequestAttribute String email) throws Exception {
        try {
            long growthId = growthService.registEvent(email, growthDto);
            return new ResponseEntity<Long>(growthId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Long>(0L, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // 성장 이벤트 단건 조회
    @Operation(summary = "성장 이벤트 조회", description = "성장 이벤트 단건 조회")
    @GetMapping("/get_event")
    public ResponseEntity<GrowthDto> getEvent(@RequestParam Long growthId) throws Exception {
        GrowthDto growthDto = growthService.getOneEvent(growthId);
        return new ResponseEntity<GrowthDto>(growthDto, HttpStatus.OK);
    }

    // 해당 성장 이벤트에 참여하는 모든 사용자 조회 (작성자 제외 참여자만)
    @Operation(summary = "참여 회원 조회", description = "해당 이벤트에 참여하는 모든 회원 조회")
    @GetMapping("/get_user")
    public ResponseEntity<List<UserDto>> getAllThisUser(@RequestParam Long growthId) throws Exception {
        List<UserDto> userDtoList = growthService.getAllThisUser(growthId);
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 해당 성장 이벤트에 참여하지 않는 모든 사용자 조회 (작성자 + 참여자 모두 제외한 나머지)
    @Operation(summary = "초대 후보 검색", description = "해당 이벤트에 참여하지 않는 모든 회원 조회")
    @GetMapping("/invite/get")
    public ResponseEntity<List<UserDto>> getAllOtherUser(@RequestParam long growthId, @RequestAttribute String email) throws Exception {
        List<UserDto> userDtoList = growthService.getAllUser(growthId, email);
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 이름으로 회원 like 검색
    @Operation(summary = "초대 후보 이름 검색", description = "해당 이벤트에 참여하지 않는 회원 중 이름으로 검색")
    @PostMapping("/invite/search")
    public ResponseEntity<List<UserDto>> searchOtherUser(@RequestBody Info info, @RequestAttribute String email) {
        List<UserDto> userDtoList = growthService.searchUser(info.getGrowthId(), email, info.getUserName());
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 성장 이벤트 회원 초대
    @Operation(summary = "성장 이벤트 회원 초대", description = "성장 이벤트 회원 초대 (한 명씩 추가)")
    @PostMapping("/invite")
    public ResponseEntity<String> inviteUser(@RequestBody Info info) throws Exception {
        String inviteResult = growthService.inviteUser(info.getGrowthId(), info.getUserId());
        return new ResponseEntity<String>(inviteResult, HttpStatus.OK);
    }

    // 내가 참여하는 모든 이벤트 조회
    @Operation(summary = "내 이벤트 조회", description = "내가 작성한, 참여한 이벤트 모두 조회")
    @GetMapping("/get_mine")
    public ResponseEntity<List<GrowthDto>> getMyAllEvent(@RequestAttribute String email) throws Exception {
        List<GrowthDto> growthDtoList = growthService.getMyAllEvent(email);
        return new ResponseEntity<List<GrowthDto>>(growthDtoList, HttpStatus.OK);
    }

    // 내가 받은 초대 조회
    @Operation(summary = "내 초대 조회", description = "초대된 성장 이벤트 모두 조회")
    @GetMapping("/invitation")
    public ResponseEntity<List<GrowthDto>> getInvitation(@RequestAttribute String email) throws Exception {
        List<GrowthDto> growthDtoList = growthService.getInvitation(email);
        return new ResponseEntity<List<GrowthDto>>(growthDtoList, HttpStatus.OK);
    }

    // 초대 수락 or 거절
    @Operation(summary = "초대 수락 or 거절", description = "초대된 성장 이벤트를 수락하거나 거절")
    @PutMapping("/invitation/{growthId}/{accept}")
    public ResponseEntity<String> acceptInvitation(@PathVariable long growthId, @PathVariable boolean accept, @RequestAttribute String email) throws Exception {
        String result = growthService.acceptInvitation(growthId, accept, email);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    // 성장 과정 등록
    @Operation(summary = "성장 과정 등록", description = "성장 과정 등록")
    @PostMapping("/write")
    public ResponseEntity<String> registProgress(@RequestBody ProgressDto progressDto, @RequestAttribute String email) throws Exception {
        try {
            String result = growthService.registProgress(progressDto, email);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // progress 단건 조회
    @Operation(summary = "progress 단건 조회", description = "하나의 progress 조회")
    @GetMapping("/progress/{progressId}")
    public ResponseEntity<ProgressDto> getProgress(@PathVariable long progressId) {
        return new ResponseEntity<ProgressDto>(growthService.getProgress(progressId), HttpStatus.OK);
    }
    
    // 하루에 대한 모든 progress를 우선순위 반영하여 조회
    @Operation(summary = "progress 하루 다건 조회", description = "하루에 포함된 모든 progress를 우선순위 반영 후 반환")
    @GetMapping("/progress/{growthId}/{date}")
    public ResponseEntity<List<ProgressDto>> getDateProgress(@PathVariable long growthId, @PathVariable String date, @RequestAttribute String email) {
        return new ResponseEntity<List<ProgressDto>>(growthService.getDateProgress(growthId, date, email), HttpStatus.OK);
    }

    // 한 이벤트의 모든 progress 조회, 데이터 가공
    @Operation(summary = "progress 다건 조회", description = "한 이벤트의 모든 progress를 데이터 가공 후 반환")
    @GetMapping("/progress")
    public ResponseEntity<List<AllProgress>> getAllProgress(@RequestParam long growthId) {
        return new ResponseEntity<List<AllProgress>>(growthService.getAllProgress(growthId), HttpStatus.OK);
    }

    // 우선순위를 반영하여 날짜별 대표 progress를 데이터 가공 후 반환
    @Operation(summary = "대표 progress", description = "우선순위를 반영한 날짜별 대표 progress를 데이터 가공 후 반환")
    @GetMapping("/progress/first")
    public ResponseEntity<List<FirstProgress>> getFirstProgress(@RequestParam long growthId, @RequestAttribute String email) {
        return new ResponseEntity<List<FirstProgress>>(growthService.getFirstProgress(growthId, email), HttpStatus.OK);
    }

    // 성장 과정 좋아요 or 좋아요 취소
    @Operation(summary = "좋아요", description = "좋아요 or 좋아요 취소")
    @PutMapping("/like/{progressId}")
    public ResponseEntity<Boolean> like(@PathVariable long progressId, @RequestAttribute String email) {
        boolean likeResult = growthService.like(progressId, email);
        return new ResponseEntity<Boolean>(likeResult, HttpStatus.OK);
    }

    // 좋아요 조회
    @Operation(summary = "좋아요 조회", description = "한 이벤트 안에서 내가 좋아요한 모든 progress 조회")
    @GetMapping("/like/get")
    public ResponseEntity<List<Long>> getLikeProgress(@RequestParam long growthId, @RequestAttribute String email) {
        return new ResponseEntity<List<Long>>(growthService.getLikeProgress(growthId, email), HttpStatus.OK);
    }

    // 로그 조회
    @Operation(summary = "로그 조회", description = "한 이벤트의 날짜별로 과정이 존재하는지 반환")
    @GetMapping("/log/{growthId}")
    public ResponseEntity<List<Log>> getLog(@PathVariable long growthId) throws ParseException {
        return new ResponseEntity<List<Log>>(growthService.makeLog(growthId), HttpStatus.OK);
    }
}
