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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        if (userList.isPresent()) {
            List<UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for (UserDto u : userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이메일");
                result.setId(u.getId());

                String content = u.getEmail();
                Map<String, Integer> cutResultMap = cutStr(keyword, content, false);
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                result.setUserName(u.getName());
                result.setUserEmail(content);
                result.setUserProfile(u.getImage());
                resultList.add(result);
            }
        }
        return resultList;
    }

    // 회원 검색 결과 반환 (이름)
    public List<SearchResultDto> searchUserName(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<User>> userList = userRepo.searchName(keyword);
        if (userList.isPresent()) {
            List<UserDto> userDtoList = userList.get().stream().map(UserDto::new).collect(Collectors.toList());
            for (UserDto u : userDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("회원 이름");
                result.setId(u.getId());

                String content = u.getName();
                Map<String, Integer> cutResultMap = cutStr(keyword, content, false);
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                result.setUserName(content);
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
        if (growthList.isPresent()) {
            List<GrowthDto> growthDtoList = growthList.get().stream().map(GrowthDto::new).collect(Collectors.toList());
            for (GrowthDto g : growthDtoList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("성장 여정 이벤트");
                result.setId(g.getGrowthId());

                String content = g.getCategory();
                Map<String, Integer> cutResultMap = cutStr(keyword, content, false);
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

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

                String content = p.getContent();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 검색 결과 반환 (기업명)
    public List<SearchResultDto> searchCompany(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<JobEvent>> jobEventList = searchRepo.searchCompany(userId, keyword);
        if (jobEventList.isPresent()) {
            List<CreateJobEventResponse> jobResponseList = jobEventList.get().stream().map(CreateJobEventResponse::new).collect(Collectors.toList());
            for (CreateJobEventResponse j : jobResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 기업명");
                result.setId(j.getId());

                String content = j.getCompanyName();
                Map<String, Integer> cutResultMap = cutStr(keyword, content, false);
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 서류 검색 결과 반환 (질문)
    public List<SearchResultDto> searchDocQuestion(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Document>> documentList = searchRepo.searchDocQuestion(userId, keyword);
        if (documentList.isPresent()) {
            List<DocumentResponse> documentResponseList = documentList.get().stream().map(DocumentResponse::new).collect(Collectors.toList());
            for (DocumentResponse d : documentResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 서류 질문");
                result.setId(d.getId());

                String content = d.getQuestion();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 서류 검색 결과 반환 (답변)
    public List<SearchResultDto> searchDocContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Document>> documentList = searchRepo.searchDocAnswer(userId, keyword);
        if (documentList.isPresent()) {
            List<DocumentResponse> documentResponseList = documentList.get().stream().map(DocumentResponse::new).collect(Collectors.toList());
            for (DocumentResponse d : documentResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 서류 답변");
                result.setId(d.getId());

                String content = d.getAnswer();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 코딩테스트 검색 결과 반환 (내용)
    public List<SearchResultDto> searchCodingContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<CodingTest>> codingTestList = searchRepo.searchCodingContent(userId, keyword);
        if (codingTestList.isPresent()) {
            List<CodingTestResponse> codingTestResponseList = codingTestList.get().stream().map(CodingTestResponse::new).collect(Collectors.toList());
            for (CodingTestResponse c : codingTestResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 코딩테스트 내용");
                result.setId(c.getId());

                String content = c.getContent();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 면접 검색 결과 반환 (질문)
    public List<SearchResultDto> searchInterviewQuestion(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Interview>> interviewList = searchRepo.searchInterviewQuestion(userId, keyword);
        if (interviewList.isPresent()) {
            List<InterviewResponse> interviewResponseList = interviewList.get().stream().map(InterviewResponse::new).collect(Collectors.toList());
            for (InterviewResponse i : interviewResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 면접 질문");
                result.setId(i.getId());

                String content = i.getQuestion();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 취업 여정 면접 검색 결과 반환 (답변)
    public List<SearchResultDto> searchInterviewAnswer(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<Interview>> interviewList = searchRepo.searchInterviewAnswer(userId, keyword);
        if (interviewList.isPresent()) {
            List<InterviewResponse> interviewResponseList = interviewList.get().stream().map(InterviewResponse::new).collect(Collectors.toList());
            for (InterviewResponse i : interviewResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 면접 답변");
                result.setId(i.getId());

                String content = i.getAnswer();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));
            }
        }
        return resultList;
    }

    // 취업 여정 기타 검색 결과 반환 (내용)
    public List<SearchResultDto> searchEtcContent(List<SearchResultDto> resultList, long userId, String keyword) {
        Optional<List<StepEtc>> stepEtcList = searchRepo.searchEtcContent(userId, keyword);
        if (stepEtcList.isPresent()) {
            List<StepEtcResponse> stepEtcResponseList = stepEtcList.get().stream().map(StepEtcResponse::new).collect(Collectors.toList());
            for (StepEtcResponse s : stepEtcResponseList) {
                SearchResultDto result = new SearchResultDto();
                result.setType("취업 여정 기타 내용");
                result.setId(s.getId());

                String content = s.getContent();
                Map<String, Integer> cutResultMap = new HashMap<>();
                if(content.length() > 30) {
                    cutResultMap = cutStr(keyword, content, true);
                } else {
                    cutResultMap = cutStr(keyword, content, false);
                }
                Map<String, String> makeResultMap = makeResult(keyword, content, cutResultMap);

                result.setPreStr(makeResultMap.get("preStr"));
                result.setKeyword(makeResultMap.get("keyword"));
                result.setNextStr(makeResultMap.get("nxtStr"));

                resultList.add(result);
            }
        }
        return resultList;
    }

    // 검색 결과를 세 블록으로 나눠 리턴 (keyword 이전의 내용, keyword, keyword 이후의 내용)
    public Map<String, Integer> cutStr(String keyword, String content, boolean isLong) {
        Map<String, Integer> resultMap = new HashMap<>();
        int preStartIdx = 0;
        int keyStartIdx = 0;
        int nxtStartIdx = 0;
        int endIdx = 0;

        for (int i = 0; i < content.length(); i++) {
            // keyword 찾기
            boolean isEqual = true;
            for (int j = 0; j < keyword.length(); j++) {
                if (content.charAt(i + j) != keyword.charAt(j)) {
                    isEqual = false;
                    break;
                }
            }

            // keyword 찾기에 성공하여 그 위치를 추출
            if (isEqual) {
                if (isLong) {
                    Map<String, Integer> idxMap = findLongIdx(keyword, content, i);
                    preStartIdx = idxMap.get("preStartIdx");
                    nxtStartIdx = idxMap.get("nxtStartIdx");
                    endIdx = idxMap.get("endIdx");
                } else {
                    nxtStartIdx = i + keyword.length();
                    endIdx = content.length() - 1;
                }

                resultMap.put("preStartIdx", preStartIdx);
                resultMap.put("keyStartIdx", i);
                resultMap.put("nxtStartIdx", nxtStartIdx);
                resultMap.put("endIdx", endIdx);

                break;
            }
        }

        return resultMap;
    }

    // 30자를 초과하는 검색 결과에 대한 연산
    public Map<String, Integer> findLongIdx(String keyword, String content, int keyStartIdx) {
        Map<String, Integer> resultMap = new HashMap<>();
        int preStartIdx = -1;
        int nxtStartIdx = keyStartIdx + keyword.length();
        int endIdx = 30 - 1;

        // keyword 를 포함한 이후 문자의 개수가 30개 미만일 경우, keyword 이전의 문자열도 포함하도록 함
        int keyToEnd = content.length() - keyStartIdx + keyword.length();
        if (keyToEnd < MAX_STR) {
            preStartIdx = keyStartIdx - (30 - keyToEnd);
        }

        resultMap.put("preStartIdx", preStartIdx);
        resultMap.put("nxtStartIdx", nxtStartIdx);
        resultMap.put("endIdx", endIdx);

        return resultMap;
    }

    // 세 블록으로 나누어 반환
    public Map<String, String> makeResult(String keyword, String content, Map<String, Integer> infoMap) {
        int preStartIdx = infoMap.get("preStartIdx");
        int keyStartIdx = infoMap.get("keyStartIdx");
        int nxtStartIdx = infoMap.get("nxtStartIdx");
        int endIdx = infoMap.get("endIdx");

        Map<String, String> resultMap = new HashMap<>();
        if (preStartIdx < 0) {
            resultMap.put("preStr", "");
        } else {
            resultMap.put("preStr", content.substring(preStartIdx, keyStartIdx));
        }

        resultMap.put("keyword", keyword);
        resultMap.put("nxtStr", content.substring(nxtStartIdx, endIdx + 1));

        return resultMap;
    }
}