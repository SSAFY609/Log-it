package com.ssafy.logit.controller.user;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    // 회원 삽입 or 수정
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println("===== saveUser =====");
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
        System.out.println("===== getAllUser =====");
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    // email로 회원 조회
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) throws Exception {
        System.out.println("===== getUser =====");
        return new ResponseEntity<UserDto>(userService.getUser(email), HttpStatus.OK);
    }

    // 회원 삭제 (실제 삭제x, isDeleted 1로 업데이트)
    @PutMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws  Exception {
        System.out.println("===== deleteUser =====");
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
        System.out.println("===== dropUser =====");
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