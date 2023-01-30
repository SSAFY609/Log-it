package com.ssafy.logit.model.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "메일")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    @Schema(description = "받는 이메일 주소")
    private String toAddress;

    @Schema(description = "보내는 이메일 주소")
    private String fromAddress;

    @Schema(description = "이메일 제목")
    private String title;

    @Schema(description = "이메일 내용")
    private String message;
}
