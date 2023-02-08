package com.ssafy.logit.controller.growth;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.ProgressDto;
import com.ssafy.logit.model.growth.service.GrowthService;
import com.ssafy.logit.model.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/growth")
@Tag(name="growth", description="성장 여정 API")
public class GrowthController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String UNAUTHORIZED = "unauthorized";
    private static final String DELETED = "이미 삭제됨";
    private static final String NONE = "사용자 없음";

    @Autowired
    private GrowthService growthService;

    static class Info {
        long growthId;
        long userId;
        String userName;

        public Info(long growthId, long userId, String userName) {
            this.growthId = growthId;
            this.userId = userId;
            this.userName = userName;
        }
    }

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

    @Operation(summary = "초대 후보 검색", description = "해당 이벤트에 참여하지 않는 회원을 모두 검색")
    @GetMapping("/invite/get")
    public ResponseEntity<List<UserDto>> getAllUser(@RequestParam long growthId, @RequestAttribute String email) throws Exception {
        List<UserDto> userDtoList = growthService.getAllUser(growthId, email);
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    @Operation(summary = "초대 후보 이름 검색", description = "해당 이벤트에 참여하지 않는 회원 중 이름으로 검색")
    @GetMapping("/invite/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestBody Info info, @RequestAttribute String email) {
        List<UserDto> userDtoList = growthService.searchUser(info.growthId, email, info.userName);
        return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
    }

    // 성장 이벤트 회원 초대
    @Operation(summary = "성장 이벤트 회원 초대", description = "성장 이벤트 회원 초대 (한 명씩 추가)")
    @PostMapping("/invite")
    public ResponseEntity<String> inviteUser(@RequestBody Info info) throws Exception {
        String inviteResult = growthService.inviteUser(info.growthId, info.userId);
        return new ResponseEntity<String>(inviteResult, HttpStatus.OK);
    }

    // 내가 참여하는 모든 이벤트 조회
    @Operation(summary = "내 이벤트 조회", description = "내가 작성한, 참여한 이벤트 모두 조회")
    @GetMapping("/get")
    public ResponseEntity<List<GrowthDto>> getMyAllEvent(@RequestAttribute String email) throws Exception {
        List<GrowthDto> growthDtoList = growthService.getMyAllEvent(email);
        return new ResponseEntity<List<GrowthDto>>(growthDtoList, HttpStatus.OK);
    }

    @Operation(summary = "내 초대 조회", description = "초대된 성장 이벤트 모두 조회")
    @GetMapping("/invitation")
    public ResponseEntity<List<GrowthDto>> getInvitation(@RequestAttribute String email) throws Exception {
        List<GrowthDto> growthDtoList = growthService.getInvitation(email);
        return new ResponseEntity<List<GrowthDto>>(growthDtoList, HttpStatus.OK);
    }

    @Operation(summary = "초대 수락 or 거절", description = "초대된 성장 이벤트를 수락하거나 거절")
    @PutMapping("/invitation")
    public ResponseEntity<String> acceptInvitation(@RequestParam long growthId, @RequestParam boolean accept, @RequestAttribute String email) throws Exception {
        String result = growthService.acceptInvitation(growthId, accept, email);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @Operation()
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
}
