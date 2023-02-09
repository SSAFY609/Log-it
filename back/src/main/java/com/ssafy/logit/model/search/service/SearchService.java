package com.ssafy.logit.model.search.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.ProgressDto;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.ProgressRepository;
import com.ssafy.logit.model.search.dto.SearchResultDto;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    GrowthRepository growthRepo;

    @Autowired
    ProgressRepository progressRepo;

    // 회원 검색 결과 반환 (이메일)
    public List<SearchResultDto> searchUserEmail(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<User>> userList = userRepo.searchEmail(userId, keyword);
        if(userList.isPresent()) {
            List< UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for(UserDto u: userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이메일");
                result.setId(u.getId());
                result.setContent(u.getEmail());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 회원 검색 결과 반환 (이름)
    public List<SearchResultDto> searchUserName(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<User>> userList = userRepo.searchName(userId, keyword);
        if(userList.isPresent()) {
            List< UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for(UserDto u: userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이름");
                result.setId(u.getId());
                result.setContent(u.getName());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 성장 여정 이벤트 검색 결과 반환 (카테고리)
    public List<SearchResultDto> searchGrowth(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Growth>> growthList = growthRepo.search(userId, keyword);
        if(growthList.isPresent()) {
            List<GrowthDto> growthDtoList = growthList.get().stream().map(GrowthDto::new).collect(Collectors.toList());
            for(GrowthDto g: growthDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("성장 여정 이벤트");
                result.setId(g.getGrowthId());
                result.setContent(g.getCategory());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 성장 과정 검색 결과 반환 (내용)
    public List<SearchResultDto> searchProgress(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Progress>> progressList = progressRepo.search(userId, keyword);
        if (progressList.isPresent()) {
            List<ProgressDto> progressDtoList = progressList.get().stream().map(ProgressDto::new).collect(Collectors.toList());
            for (ProgressDto p : progressDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("성장 과정");
                result.setId(p.getGrowthId());
                result.setContent(p.getContent());
                resultList.add(result);
            }
        }
        return resultList;
    }
}
