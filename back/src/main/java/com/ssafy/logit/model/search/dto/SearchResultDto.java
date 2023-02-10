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

    long id;             // 검색된 정보의 primary key

    String content;      // 검색된 내용

    String userEmail;    // 회원 이메일 (회원 이름 검색 결과에만 추가로 제공)

    String userName;     // 회원 이름 (회원 이메일 검색 결과에만 추가로 제공)

    String userProfile;  // 회원 프로필 이미지 (회원 검색 결과에만 추가로 제공)
}
