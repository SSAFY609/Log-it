package com.ssafy.logit.controller.user;

import com.ssafy.logit.jwt.JwtUtil;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) throws Exception {
        log.info("login user info : {}", userDto);
        Map<String, Object> resultMap = new HashMap<>();
        UserDto loginUser = userService.login(userDto.getEmail(), userDto.getPw());

        // 생성된 토큰 정보를 클라이언트에게 전달
        resultMap.put("jwt-auth-token", loginUser.getAuthToken());
        resultMap.put("jwt-refresh-token", loginUser.getRefreshToken());

        // 정보 확인을 위해 클라이언트로 전달
        Map<String, Object> authToken_info = jwtUtil.checkAndGetClaims(loginUser.getAuthToken());
        resultMap.putAll(authToken_info);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    // refresh-token 확인 후 auth-token 재발급
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestParam String email) {
        Map<String, Object> resultMap = new HashMap<>();
        String token = userService.getRefreshToken(email);
        jwtUtil.checkAndGetClaims(token);

        if (token.equals(userService.getRefreshToken(email))) {
            String authToken = jwtUtil.createAuthToken(email);
            resultMap.put("jwt-auth-token", authToken);
            Map<String, Object> info = jwtUtil.checkAndGetClaims(authToken);
            resultMap.putAll(info);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String email) {
        log.debug("logout : {}", email);
        userService.logout(email);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.ACCEPTED);
    }

    // 회원 삽입 or 수정
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) throws Exception {
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 전체 회원 조회
    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUser() throws Exception {
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    // email로 회원 조회
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) throws Exception {
        return new ResponseEntity<UserDto>(userService.getUser(email), HttpStatus.OK);
    }

    // 회원 삭제 (실제 삭제x, isDeleted 1로 업데이트)
    @PutMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws  Exception {
        String result = userService.deleteUser(id);
        try {
            if(result.equals("success")) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else if(result.equals("already deleted")) {
                return new ResponseEntity<String>("이미 삭제된 회원입니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("없는 회원입니다.", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 회원 삭제 (실제 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> dropUser(@PathVariable Long id) throws Exception {
        boolean result = userService.dropUser(id);
        try {
            if(result) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("없는 회원입니다.", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }
}