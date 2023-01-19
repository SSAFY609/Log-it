package com.ssafy.logit.model.user.dto;

import com.ssafy.logit.model.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
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
    private int isDeleted;
    private LocalDateTime createdTime;
    private LocalDateTime loginTime;

    public User toEntity() {
        return User.builder()
                    .name(this.name)
                    .email(this.email)
                    .pw(this.pw)
                    .flag(this.flag)
                    .studentNo(this.studentNo)
                    .image(this.image)
                    .isDeleted(this.isDeleted).build();
    }
}
