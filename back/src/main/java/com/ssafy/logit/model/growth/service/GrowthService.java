package com.ssafy.logit.model.growth.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.GrowthUserDto;
import com.ssafy.logit.model.growth.entity.Category;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.GrowthUser;
import com.ssafy.logit.model.growth.repository.CategoryRepository;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.GrowthUserRepository;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 해당 이벤트에 참여하지 않는 사용자 반환
    public List<UserDto> getAllUser(long growthId, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            long userId = user.get().getId();
            List<User> userList = userRepo.findInviteUser(growthId, userId);
            return userList.stream().map(UserDto::new).collect(Collectors.toList());
        }
        return null;
    }

    // 해당 이벤트에 참여하지 않는 사용자 중 이름으로 like 검색한 결과 반환
    public List<UserDto> searchUser(long growthId, String email, String userName) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().getId();
            List<User> userList = userRepo.searchInviteUser(growthId, userId, userName);
            return userList.stream().map(UserDto::new).collect(Collectors.toList());
        }
        return null;
    }

    // (해당 이벤트에 참여중이지 않은 사용자만 받아올 수 있으므로, 그 부분은 따로 검사하지 않음)
    public String inviteUser(long growthId, long userId) {
        Optional<Growth> growth = growthRepo.findById(growthId);
        if(growth.isPresent()) {
            GrowthUser growthUser = new GrowthUser();
            growthUser.setGrowth(growth.get());
            Optional<User> user = userRepo.findById(userId);
            if(user.isPresent()) {
                growthUser.setUser(user.get());
                growthUser.setType(false);
                growthUserRepo.save(growthUser);
                return SUCCESS;
            } else {
                return NONE;
            }
        } else {
            return NONE_EVENT;
        }
    }
}
