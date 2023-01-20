package com.ssafy.logit.model.user.service;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

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
    public String deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            UserDto userDto = user.get().toDto();
            boolean deletedStatus = userDto.isDeleted();
            if(!deletedStatus) {
                userDto.setDeleted(true);
                userRepo.save(userDto.toEntity());
                return "success";
            } else {
                return "already deleted";
            }
        } else {
            return "none";
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