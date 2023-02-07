package com.ssafy.logit.model.growth.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.GrowthUserDto;
import com.ssafy.logit.model.growth.entity.Category;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.repository.CategoryRepository;
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
    private static final String NONE_CATEGORY = "카테고리 없음";
    private static final String NONE_EVENT = "성장 이벤트 없음";

    @Autowired
    private GrowthRepository growthRepo;

    @Autowired
    private GrowthUserRepository growthUserRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    public String registEvent(String email, GrowthDto growthDto) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            growthDto.setUser(user.get());
            Optional<Category> category = categoryRepo.findByCategoryName(growthDto.getCategoryName());
            if (category.isPresent()) {
                growthDto.setCategory(category.get());
                Growth growth = growthRepo.save(growthDto.toEntity());
                String shareResult = shareEvent(growth.getGrowthId(), growthDto.getUserList());
                if (!shareResult.equals(SUCCESS)) {
                    return shareResult;
                } else {
                    return SUCCESS;
                }
            } else {
                return NONE_CATEGORY;
            }
        } else {
            return NONE;
        }
    }

    public String shareEvent(long growthId, List<Long> userList) {
        int len = userList.size();
        for (int i = 0; i < len; i++) {
            Optional<User> user = userRepo.findById(userList.get(i));
            if (user.isPresent()) {
                GrowthUserDto growthUserDto = new GrowthUserDto();
                growthUserDto.setUser(user.get());
                Optional<Growth> growth = growthRepo.findById(growthId);
                if (growth.isPresent()) {
                    growthUserDto.setGrowth(growth.get());
                    growthUserDto.setType(false);
                    growthUserRepo.save(growthUserDto.toEntity());
                } else {
                    return NONE_EVENT;
                }
            } else {
                return NONE;
            }
        }
        return SUCCESS;
    }
}
