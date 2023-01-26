package com.ssafy.logit.model.user.entity;

import com.ssafy.logit.model.user.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String pw;

    private int flag;

    @Column(length = 10)
    private String studentNo;

    @Column(length = 100)
    private String image;

    private boolean deleted;

    private String refreshToken;

    // Entity -> DTO 변환
    public UserDto toDto() {
        return UserDto.builder()
                        .id(this.id)
                        .name(this.name)
                        .email(this.email)
                        .pw(this.pw)
                        .flag(this.flag)
                        .studentNo(this.studentNo)
                        .image(this.image)
                        .deleted(this.deleted)
                        .createdTime(super.getCreatedTime())
                        .loginTime(super.getLoginTime())
                        .refreshToken(this.refreshToken).build();
    }
}
