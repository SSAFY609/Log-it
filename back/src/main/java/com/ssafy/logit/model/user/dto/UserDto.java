package com.ssafy.logit.model.user.dto;

import com.ssafy.logit.model.user.entity.DateTime;
import com.ssafy.logit.model.user.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String pw;
    private int flag;
    private String studentNo;
    private String image;
    private boolean deleted;
    private LocalDateTime createdTime;
    private LocalDateTime loginTime;

    private String authToken; // 사용자 인증 정보 토큰
    private String refreshToken; // authToken 갱신을 위한 토큰

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
                .loginTime(userDto.getLoginTime()).build();
                // createdTime, loginTime 가져와야 함
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
                    .loginTime(this.loginTime).build();
    }
}
