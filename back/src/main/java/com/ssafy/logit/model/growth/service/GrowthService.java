package com.ssafy.logit.model.growth.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.GrowthUserDto;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.GrowthUserRepository;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrowthService {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String DELETED = "이미 삭제됨";
    private static final String NONE = "사용자 없음";
    private static final String IS_LOGINED = "이미 로그인된 사용자";
    private static final String PW_FAIL = "비밀번호 틀림";
    private static final String PRESENT = "이미 가입된 사용자";

    @Autowired
    private GrowthRepository growthRepo;

    @Autowired
    private GrowthUserRepository growthUserRepo;

    @Autowired
    private UserRepository userRepo;

    public String registEvent(String email, GrowthDto growthDto, List<Long> userList) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            growthDto.setUser(user.get());
            Growth growth = growthRepo.save(growthDto.toEntity());
            shareEvent(growth.getGrowthId(), userList);
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    public String shareEvent(Long growthId, List<Long> userList) {
        int len = userList.size();
        for(int i = 0; i < len; i++) {
            Optional<User> user = userRepo.findById(userList.get(i));
            if(user.isPresent()) {
                GrowthUserDto growthUserDto = new GrowthUserDto();
                growthUserDto.setUser(user.get());
                Optional<Growth> growth = growthRepo.findById(growthId);
                if(growth.isPresent()) {
                    growthUserDto.setGrowth(growth.get());
                    growthUserDto.setType(false);
                    growthUserRepo.save(growthUserDto.toEntity());
                } else {
                    return FAIL;
                }
            } else {
                return FAIL;
            }
        }
        return SUCCESS;
    }
}
