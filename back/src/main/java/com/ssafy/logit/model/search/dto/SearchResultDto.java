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
}
