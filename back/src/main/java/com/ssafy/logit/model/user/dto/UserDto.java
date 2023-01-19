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

    // List<Entity> -> List<DTO> 변환을 위함
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.flag = user.getFlag();
        this.studentNo = user.getStudentNo();
        this.isDeleted = user.getIsDeleted();
        this.createdTime = user.toDto().getCreatedTime();
        this.loginTime = user.toDto().getLoginTime();
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
                    .isDeleted(this.isDeleted).build();
                    // createdTime, loginTime 가져와야 함
    }
}
