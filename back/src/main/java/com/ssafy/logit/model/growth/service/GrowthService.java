package com.ssafy.logit.model.growth.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.GrowthUserDto;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.GrowthUser;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.GrowthUserRepository;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrowthService {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String NONE = "사용자 없음";
    private static final String NONE_EVENT = "성장 이벤트 없음";

    @Autowired
    private GrowthRepository growthRepo;

    @Autowired
    private GrowthUserRepository growthUserRepo;

    @Autowired
    private UserRepository userRepo;

    // 이벤트 등록
    public String registEvent(String email, GrowthDto growthDto) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            growthDto.setUser(user.get());
            growthRepo.save(growthDto.toEntity());
            return SUCCESS;
        } else {
            return NONE;
        }
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

    // 성장 이벤트 공유
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

    // 내 모든 이벤트 반환 (참여 + 작성)
    public List<GrowthDto> getMyAllEvent(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().getId();

            // 내가 작성한 이벤트 조회
            List<Growth> growthList = growthRepo.findByUserId(userId);
            List<GrowthDto> growthDtoList = growthList.stream().map(GrowthDto::new).collect(Collectors.toList());

            // 내가 참여하는 이벤트 조회
            List<GrowthUser> growthUserList= growthUserRepo.findMyEvent(userId, true);
            List<GrowthUserDto> growthUserDtoList = growthUserList.stream().map(GrowthUserDto::new).collect(Collectors.toList());
            for(int i = 0; i < growthUserDtoList.size(); i++) {
                Optional<Growth> tmp = growthRepo.findById(growthUserDtoList.get(i).getGrowth().getGrowthId());
                if(tmp.isPresent()) {
                    growthDtoList.add(tmp.get().toDto());
                }
            }
            return growthDtoList;
        }
        return null;
    }

    // 한 개의 성장 이벤트 반환
    public GrowthDto getOneEvent(long growthUserId) {
        Optional<Growth> growth = growthRepo.findById(growthUserId);
        if(growth.isPresent()) {
            return growth.get().toDto();
        }
        return null;
    }

    // 내가 받은 초대에 대한 이벤트 객체들을 반환
    public List<GrowthDto> getInvitation(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            // 아직 수락하지 않은 초대 모두 조회
            long userId = user.get().toDto().getId();
            List<GrowthUser> growthUserList = growthUserRepo.findMyEvent(userId, false);
            List<GrowthUserDto> growthUserDtoList = growthUserList.stream().map(GrowthUserDto::new).collect(Collectors.toList());

            // 아직 수락하지 않은 초대의 이벤트 객체를 List로 만들어 반환
            List<GrowthDto> growthDtoList  = new ArrayList<>();
            for(int i = 0; i < growthUserList.size(); i++) {
                Optional<Growth> growth = growthRepo.findById(growthUserList.get(i).toDto().getGrowth().getGrowthId());
                if(growth.isPresent()) {
                    growthDtoList.add(growth.get().toDto());
                }
            }
            return growthDtoList;
        }
        return null;
    }

    public String acceptInvitation(long growthId, boolean accept, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().toDto().getId();
            long growthUserId = growthUserRepo.findEvent(userId, growthId);
            Optional<GrowthUser> growthUser = growthUserRepo.findById(growthUserId);
            if(growthUser.isPresent()) {
                GrowthUserDto growthUserDto = growthUser.get().toDto();
                if(accept) {
                    growthUserDto.setType(true);
                    growthUserRepo.save(growthUserDto.toEntity());
                } else {
                    growthUserRepo.delete(growthUser.get());
                }
                return SUCCESS;
            } else {
                return FAIL;
            }
        }
        return FAIL;
    }
}
