package com.ssafy.logit.model.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDto {

    String type;         // 검색 결과 유형
    boolean isLong;      // 검색 결과가 30자 이상인지 여부
    long id;             // 검색된 정보의 primary key

    String preStr;       // 검색된 내용 중 keyword 앞에 위치하는 내용
    String keyword;      // 검색된 keyword
    String nextStr;      // 검색된 내용 중 keyword 뒤에 위치하는 내용

    // 회원 이름 검색 결과에만 추가 제공
    String userEmail;    // 회원 이메일
    String userName;     // 회원 이름
    String userProfile;  // 회원 프로필 이미지

    // 성장 이벤트 검색 결과에만 추가 제공
    String startDate;
    String endDate;

    // 성장 과정 or 취업 내용 검색 결과에만 추가 제공
    String eventName;
}
