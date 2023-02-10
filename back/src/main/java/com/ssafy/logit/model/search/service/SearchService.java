package com.ssafy.logit.model.search.service;

import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.growth.dto.ProgressDto;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.ProgressRepository;
import com.ssafy.logit.model.job.dto.CreateJobEventResponse;
import com.ssafy.logit.model.job.entity.JobEvent;
import com.ssafy.logit.model.search.Repository.SearchRepository;
import com.ssafy.logit.model.search.dto.SearchResultDto;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CodingTestResponse;
import com.ssafy.logit.model.step_category.dto.category.document.DocumentResponse;
import com.ssafy.logit.model.step_category.dto.category.etc.StepEtcResponse;
import com.ssafy.logit.model.step_category.dto.category.interview.InterviewResponse;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import com.ssafy.logit.model.step_category.entity.category.StepEtc;
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

    private static final int MAX_STR = 30;

    @Autowired
    UserRepository userRepo;

    @Autowired
    GrowthRepository growthRepo;

    @Autowired
    ProgressRepository progressRepo;

    @Autowired
    SearchRepository searchRepo;

    // 회원 검색 결과 반환 (이메일)
    public List<SearchResultDto> searchUserEmail(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<User>> userList = userRepo.searchEmail(keyword);
        if(userList.isPresent()) {
            List< UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for(UserDto u: userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이메일");
                result.setId(u.getId());
                result.setContent(u.getEmail());
                result.setUserName(u.getName());
                result.setUserProfile(u.getImage());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 회원 검색 결과 반환 (이름)
    public List<SearchResultDto> searchUserName(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<User>> userList = userRepo.searchName(keyword);
        if(userList.isPresent()) {
            List< UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for(UserDto u: userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이름");
                result.setId(u.getId());
                result.setContent(u.getName());
                result.setUserEmail(u.getEmail());
                result.setUserProfile(u.getImage());
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
                result.setId(p.getProgressId());

                if(p.getContent().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, p.getContent());
                    result.setContent(cutResult);
                } else {
                    result.setContent(p.getContent());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 검색 결과 반환 (기업명)
    public List<SearchResultDto> searchCompany(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<JobEvent>> jobEventList = searchRepo.searchCompany(userId, keyword);
        if(jobEventList.isPresent()) {
            List<CreateJobEventResponse> jobResponseList = jobEventList.get().stream().map(CreateJobEventResponse::new).collect(Collectors.toList());
            for(CreateJobEventResponse j: jobResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 기업명");
                result.setId(j.getId());
                result.setContent(j.getCompanyName());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 서류 검색 결과 반환 (질문)
    public List<SearchResultDto> searchDocQuestion(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Document>> documentList = searchRepo.searchDocQuestion(userId, keyword);
        if(documentList.isPresent()) {
            List<DocumentResponse> documentResponseList = documentList.get().stream().map(DocumentResponse::new).collect(Collectors.toList());
            for(DocumentResponse d: documentResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 서류 질문");
                result.setId(d.getId());

                if(d.getQuestion().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, d.getQuestion());
                    result.setContent(cutResult);
                } else {
                    result.setContent(d.getQuestion());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 서류 검색 결과 반환 (답변)
    public List<SearchResultDto> searchDocContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Document>> documentList = searchRepo.searchDocAnswer(userId, keyword);
        if(documentList.isPresent()) {
            List<DocumentResponse> documentResponseList = documentList.get().stream().map(DocumentResponse::new).collect(Collectors.toList());
            for(DocumentResponse d: documentResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 서류 답변");
                result.setId(d.getId());

                if(d.getAnswer().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, d.getAnswer());
                    result.setContent(cutResult);
                } else {
                    result.setContent(d.getAnswer());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 코딩테스트 검색 결과 반환 (내용)
    public List<SearchResultDto> searchCodingContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<CodingTest>> codingTestList = searchRepo.searchCodingContent(userId, keyword);
        if(codingTestList.isPresent()) {
            List<CodingTestResponse> codingTestResponseList = codingTestList.get().stream().map(CodingTestResponse::new).collect(Collectors.toList());
            for(CodingTestResponse c: codingTestResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 코딩테스트 내용");
                result.setId(c.getId());

                if(c.getContent().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, c.getContent());
                    result.setContent(cutResult);
                } else {
                    result.setContent(c.getContent());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 면접 검색 결과 반환 (질문)
    public List<SearchResultDto> searchInterviewQuestion(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Interview>> interviewList = searchRepo.searchInterviewQuestion(userId, keyword);
        if(interviewList.isPresent()) {
            List<InterviewResponse> interviewResponseList = interviewList.get().stream().map(InterviewResponse::new).collect(Collectors.toList());
            for(InterviewResponse i: interviewResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 면접 질문");
                result.setId(i.getId());

                if(i.getQuestion().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, i.getQuestion());
                    result.setContent(cutResult);
                } else {
                    result.setContent(i.getQuestion());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 면접 검색 결과 반환 (답변)
    public List<SearchResultDto> searchInterviewAnswer(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Interview>> interviewList = searchRepo.searchInterviewAnswer(userId, keyword);
        if(interviewList.isPresent()) {
            List<InterviewResponse> interviewResponseList = interviewList.get().stream().map(InterviewResponse::new).collect(Collectors.toList());
            for(InterviewResponse i: interviewResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 면접 답변");
                result.setId(i.getId());

                if(i.getAnswer().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, i.getAnswer());
                    result.setContent(cutResult);
                } else {
                    result.setContent(i.getAnswer());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 기타 검색 결과 반환 (내용)
    public List<SearchResultDto> searchEtcContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<StepEtc>> stepEtcList = searchRepo.searchEtcContent(userId, keyword);
        if(stepEtcList.isPresent()) {
            List<StepEtcResponse> stepEtcResponseList = stepEtcList.get().stream().map(StepEtcResponse::new).collect(Collectors.toList());
            for(StepEtcResponse s: stepEtcResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 기타 내용");
                result.setId(s.getId());

                if(s.getContent().length() > MAX_STR) {
                    String cutResult = cutStr(keyword, s.getContent());
                    result.setContent(cutResult);
                } else {
                    result.setContent(s.getContent());
                }
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 문자열 가공 (30자 이내, 키워드 포함)
    public String cutStr(String keyword, String content) {
        char[] contentArr = content.toCharArray();
        char[] keywordArr = keyword.toCharArray();

        for(int i = 0; i < content.length(); i++) {
            if(contentArr[i] == keywordArr[0]) {
                boolean isHere = false;
                for(int j = 0; j < keyword.length(); j++) {
                    if(contentArr[i+j] != keywordArr[j]) {
                        isHere = false;
                        break;
                    } else {
                        isHere = true;
                    }
                }

                // 검색 keyword의 위치 찾고 검색 결과 반환
                if(isHere) {
                    int keyStartIdx = i;
                    int keyNextIdx = i + keyword.length();
                    int nextContentLen = content.length() - keyNextIdx; // content에서 keyword 이후에 남은 문자열 길이

                    char[] result = new char[MAX_STR];
                    if(nextContentLen >= MAX_STR - keyword.length()) {
                        for(int j = 0; j < keyword.length(); j++) {
                            result[j] = keywordArr[j];
                        }
                        for(int j = keyNextIdx; j < MAX_STR; j++) {
                            result[j] = contentArr[j];
                        }
                    } else {
                        int startIdx = content.length() - nextContentLen - keyword.length() - (30 - nextContentLen - keyword.length());
                        int idx = 0;

                        for(int j = startIdx; j < keyStartIdx; j++) {
                            result[idx++] = contentArr[j];
                        }
                        for(int j = keyStartIdx; j < keyNextIdx; j++) {
                            result[idx++] = contentArr[j];
                        }
                        for(int j = keyNextIdx; j < content.length(); j++) {
                            result[idx++] = contentArr[j];
                        }
                    }
                    return new String(result);
                }
            }
        }
        return null;
    }
}
