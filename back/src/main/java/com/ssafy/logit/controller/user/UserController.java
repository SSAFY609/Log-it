package com.ssafy.logit.controller.user;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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