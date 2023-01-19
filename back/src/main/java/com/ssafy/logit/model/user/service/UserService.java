package com.ssafy.logit.model.user.service;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void insertUser(UserDto userDto) {
        User user = userDto.toEntity();
        userRepo.save(user);
    }

//    public List<UserDto> getAllUser() {
//        List<User> allUser = userRepo.findAll();
//        List<UserDto> allUserDto = new ArrayList<>();
//
//        for(User user : allUser) {
//            UserDto userDto = UserDto.builder()
//                    .name(user.getName())
//                    .email(user.getEmail())
//                    .pw(user.getPw())
//                    .flag(user.getFlag())
//                    .studentNo(user.getStudentNo())
//                    .image(user.getImage())
//                    .isDeleted(user.getIsDeleted())
//                    .createdTime(user.getCreatedTime())
//                    .loginTime(user.getLoginTime()).build();
//
//            allUserDto.add(userDto);
//        }
//
//        return allUserDto;
//    }

//    public UserDto getUser(String email) {
//        return userRepo.findByEmail(email);
//    }

//    @Transactional
//    public void insertUser(UserDto userDto) {
//        userRepo.save(userDto.toEntity());
//    }
//
//    @Transactional
//    public void updateUser(UserDto userDto) {
//        Optional<User> byId = userRepo.findById(userDto.getId());
//        User user = byId.get();
//        user.update(userDto);
//    }
}