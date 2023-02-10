package com.ssafy.logit.controller.search;

import com.ssafy.logit.model.search.dto.SearchResultDto;
import com.ssafy.logit.model.search.service.SearchService;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
@Tag(name="search", description="검색 API")
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    @Operation(summary = "", description = "")
    @GetMapping
    public ResponseEntity<List<SearchResultDto>> search(@RequestParam String keyword, @RequestAttribute String email) {
        long userId = userService.getUser(email).getId();
        List<SearchResultDto> resultList = new ArrayList<>();
        resultList = searchService.searchUserEmail(resultList, userId, keyword);
        resultList = searchService.searchUserName(resultList, userId, keyword);
        resultList = searchService.searchGrowth(resultList, userId, keyword);
        resultList = searchService.searchProgress(resultList, userId, keyword);
        resultList = searchService.searchCompany(resultList, userId, keyword);
        resultList = searchService.searchDocQuestion(resultList, userId, keyword);
        resultList = searchService.searchDocContent(resultList, userId, keyword);
        resultList = searchService.searchCodingContent(resultList, userId, keyword);
        resultList = searchService.searchInterviewQuestion(resultList, userId, keyword);
        resultList = searchService.searchInterviewAnswer(resultList, userId, keyword);
        resultList = searchService.searchEtcContent(resultList, userId, keyword);
        return new ResponseEntity<List<SearchResultDto>>(resultList, HttpStatus.OK);
    }
}
