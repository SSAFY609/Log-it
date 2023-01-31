package com.ssafy.logit.model.user.dto;

import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Schema(description = "회원")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(description = "아이디 (auto_increment)")
    private Long id;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String pw;

    @Schema(description = "기수")
    private int flag;

    @Schema(description = "학번")
    private String studentNo;

    @Schema(description = "프로필 이미지")
    private String image;

    @Schema(description = "탈퇴 여부")
    private boolean deleted;

    @Schema(description = "가입 시간")
    private LocalDateTime createdTime;

    @Schema(description = "마지막 업데이트 시간")
    private LocalDateTime loginTime;

    @Schema(description = "access 토큰")
    private String authToken; // 사용자 인증 정보 토큰

    @Schema(description = "refresh 토큰")
    private String refreshToken; // authToken 갱신을 위한 토큰

    @Schema(description = "refresh 토큰")
    private String imageUrl;

    // List<Entity> -> List<DTO> 변환을 위함
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.flag = user.getFlag();
        this.studentNo = user.getStudentNo();
        this.deleted = user.isDeleted();
        this.createdTime = user.getCreatedTime();
        this.loginTime = user.getLoginTime();
        this.refreshToken = user.getRefreshToken();
    }

    public User updateUser(Long id, UserDto userDto) {
        return User.builder()
                .id(id)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .pw(userDto.getPw())
                .flag(userDto.getFlag())
                .studentNo(userDto.getStudentNo())
                .image(userDto.getImage())
                .deleted(userDto.isDeleted())
                .createdTime(userDto.getCreatedTime())
                .loginTime(userDto.getLoginTime())
                .refreshToken(userDto.getRefreshToken()).build();
    }

    // DTO -> Entity 변환
    public User toEntity() {
        return User.builder()
                    .id(this.id)
                    .name(this.name)
                    .email(this.email)
                    .pw(this.pw)
                    .flag(this.flag)
                    .studentNo(this.studentNo)
                    .image(this.image)
                    .deleted(this.deleted)
                    .createdTime(this.createdTime)
                    .loginTime(this.loginTime)
                    .refreshToken(this.refreshToken).build();
    }
}
