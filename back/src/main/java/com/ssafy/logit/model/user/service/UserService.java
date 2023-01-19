package com.ssafy.logit.model.user.service;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void insertUser(UserDto userDto) {
        User user = userDto.toEntity();
        userRepo.save(user);
    }

    public List<UserDto> getAllUser() {
        // findAll()의 반환형은 List<User>이므로, stream 사용하여 List<UserDto>로 변환
        return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getUser(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            return userRepo.findByEmail(email).get().toDto();
        } else {
            return null;
        }
    }

    @Transactional
    public boolean dropUser(Long id) {
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}