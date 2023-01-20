package com.ssafy.logit.model.user.entity;

import com.ssafy.logit.model.user.dto.UserDto;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String pw;

    @Column(nullable = false)
    private int flag;

    @Column(length = 10, nullable = false)
    private String studentNo;

    @Column(length = 100)
    private String image;

    @Column(nullable = false)
    private boolean deleted;

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
                .deleted(this.deleted).build();
                // createdTime, loginTime 가져와야 함
    }
}
