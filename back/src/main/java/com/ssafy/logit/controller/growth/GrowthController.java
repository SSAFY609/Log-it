package com.ssafy.logit.controller.growth;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.ProgressDto;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.growth.service.GrowthService;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.TemporalField;
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
    private static final String IS_LOGINED = "이미 로그인된 사용자";
    private static final String PW_FAIL = "비밀번호 틀림";
    private static final String PRESENT = "이미 가입된 사용자";
    private static final String EXPIRED = "token expired";

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
    public ResponseEntity<String> registEvent(@RequestBody GrowthDto growthDto, @RequestAttribute String email) throws Exception {
        try {
            String registResult = growthService.registEvent(email, growthDto);
            if(registResult.equals(SUCCESS)) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(registResult, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "초대 후보 검색", description = "해당 이벤트에 참여하지 않는 회원을 모두 검색")
    @GetMapping("/invite/get")
    public ResponseEntity<List<UserDto>> getAllUser(@RequestParam long growthId, @RequestAttribute String email) throws Exception {
        List<UserDto> userList = growthService.getAllUser(growthId, email);
        return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
    }

    @Operation(summary = "초대 후보 이름 검색", description = "해당 이벤트에 참여하지 않는 회원 중 이름으로 검색")
    @GetMapping("/invite/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestBody Info info, @RequestAttribute String email) {
        List<UserDto> userList = growthService.searchUser(info.growthId, email, info.userName);
        return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
    }

    // 성장 이벤트 회원 초대
    @Operation(summary = "성장 이벤트 회원 초대", description = "성장 이벤트 회원 초대 (한 명씩 추가)")
    @PostMapping("/invite")
    public ResponseEntity<String> inviteUser(@RequestBody Info info) throws Exception {
        String inviteResult = growthService.inviteUser(info.growthId, info.userId);
        return new ResponseEntity<String>(inviteResult, HttpStatus.OK);
    }
}
