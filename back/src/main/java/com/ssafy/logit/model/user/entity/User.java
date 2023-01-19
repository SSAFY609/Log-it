package com.ssafy.logit.model.user.entity;

import com.ssafy.logit.model.user.dto.UserDto;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
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
    private int isDeleted;

//    public void updateUser(UserDto userDto) {
//        this.name = userDto.getName();
//        this.email = userDto.getEmail();
//        this.pw = userDto.getPw();
//        this.flag = userDto.getFlag();
//        this.studentNo = userDto.getStudentNo();
//        this.image = userDto.getImage();
//        this.isDeleted = userDto.getIsDeleted();
//    }
}
