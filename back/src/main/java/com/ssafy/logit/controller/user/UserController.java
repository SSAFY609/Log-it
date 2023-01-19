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

    @PostMapping
    public ResponseEntity<String> insertUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println("===== insertUser =====");
        try {
            userService.insertUser(userDto);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUser() throws Exception {
        System.out.println("===== getAllUser =====");
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) throws Exception {
        System.out.println("===== getUser =====");
        return new ResponseEntity<UserDto>(userService.getUser(email), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> dropUser(@PathVariable Long id) throws Exception {
        System.out.println("===== dropUser =====");
        boolean result = userService.dropUser(id);
        try {
            if(result) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("존재하지 않는 회원입니다.", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

//    @GetMapping
//    public List<UserDto> getAllUser() {
//        System.out.println("===== getAllUser =====");
//        return userService.getAllUser();
//    }

//    @GetMapping("/{email}")
//    public UserDto getUser(@PathVariable String email) {
//        System.out.println("===== getUser =====");
//        return userService.getUser(email);
//    }

//    @PostMapping
//    public void insertUser(@RequestBody UserDto userDto) {
//        System.out.println("===== insertUser =====");
//        userService.insertUser(userDto);
//    }
//
//    @PutMapping
//    public void updateUser(@RequestBody UserDto userDto) {
//        System.out.println("===== updateUser =====");
//        userService.updateUser(userDto);
//    }
}