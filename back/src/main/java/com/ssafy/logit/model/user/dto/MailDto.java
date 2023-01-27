package com.ssafy.logit.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String toAddress;       // 받는 이메일 주소
    private String title;           // 이메일 제목
    private String message;         // 이메일 내용
    private String fromAddress;     // 보내는 이메일 주소
}
