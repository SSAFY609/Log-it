package com.ssafy.logit.model.user.service;

import com.ssafy.logit.jwt.JwtUtil;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public UserDto login(String email, String pw) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent() && user.get().getPw().equals(pw)) {
            // 인증 성공 시 auth-token과 refresh-token 함께 발급'
            System.out.println("===== login =====");
            String authToken = jwtUtil.createAuthToken(email);
            String refreshToken = jwtUtil.createRefreshToken();
            saveRefreshToken(email, refreshToken);
            return UserDto.builder().email(email).authToken(authToken).build();
        } else {
            throw new RuntimeException("login : " + email + "에 해당하는 사용자 없음");
        }
    }

    @Transactional
    public void saveRefreshToken(String email, String refreshToken) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            System.out.println("===== saveRefreshToken =====");
            UserDto userDto = user.get().toDto();
            userDto.setRefreshToken(refreshToken);
            userRepo.save(userDto.toEntity());
       } else {
            System.out.println("saveRefreshToken : " + email + "에 해당하는 사용자 없음");
        }
    }

    @Transactional
    public void logout(String email) {
        System.out.println("===== logout =====");
        saveRefreshToken(email, null);
    }

    public String getRefreshToken(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            System.out.println("===== getRefreshToken =====");
            return user.get().toDto().getRefreshToken();
        } else {
            System.out.println("getRefreshToken : " + email + "에 해당하는 사용자 없음");
            return null;
        }
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        Optional<User> user = userRepo.findByEmail(userDto.getEmail());
        if(user.isPresent()) { // update
            System.out.println("===== updateUser =====");
            userRepo.save(userDto.updateUser(user.get().getId(), userDto));
        } else { // insert
            System.out.println("===== insertUser =====");
            userRepo.save(userDto.toEntity());
        }
    }

    public List<UserDto> getAllUser() {
        // findAll()의 반환형은 List<User>이므로, stream 사용하여 List<UserDto>로 변환
        System.out.println("===== getAllUser =====");
        return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getUser(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            System.out.println("===== getUser =====");
            return userRepo.findByEmail(email).get().toDto();
        } else {
            System.out.println("getUser : " + email + "에 해당하는 사용자 없음");
            return null;
        }
    }

    @Transactional
    public String deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            UserDto userDto = user.get().toDto();
            boolean deletedStatus = userDto.isDeleted();
            if(!deletedStatus) {
                System.out.println("===== deleteUser (회원 삭제) =====");
                userDto.setDeleted(true);
                userRepo.save(userDto.toEntity());
                return "success";
            } else {
                System.out.println("===== deleteUser (이미 삭제된 회원) =====");
                return "already deleted";
            }
        } else {
            System.out.println("deleteUser : " + id + "에 해당하는 사용자 없음");
            return "none";
        }
    }

    @Transactional
    public boolean dropUser(Long id) {
        if(userRepo.findById(id).isPresent()) {
            System.out.println("===== dropUser =====");
            userRepo.deleteById(id);
            return true;
        } else {
            System.out.println("dropUser : " + id + "에 해당하는 사용자 없음");
            return false;
        }
    }
}