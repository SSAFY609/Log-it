package com.ssafy.logit.controller.search;


import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.search.dto.UserSearchDto;
import com.ssafy.logit.model.search.service.SearchService;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/search")
@Tag(name="search",description = "검색 API")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/user")
    public ResponseEntity<ResultDto> searchUser(@RequestParam String name){
        List<User> users = searchService.searchUserList(name);
        List<UserSearchDto> collect = users.stream()
                .map(o -> new UserSearchDto(o))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ResultDto(collect.size(), collect), HttpStatus.OK);
    }
}
