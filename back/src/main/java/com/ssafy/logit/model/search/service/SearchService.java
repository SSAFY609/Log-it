package com.ssafy.logit.model.search.service;

import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {
    private final UserService userService;

    public List<User> searchUserList(String name){
        return userService.getUsersByName(name);
    }


}

