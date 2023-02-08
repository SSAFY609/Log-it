package com.ssafy.logit.model.search.dto;


import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "User 검색 결과 dto")
public class UserSearchDto {

    @Schema(description = "유저 id")
    private Long id;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "이미지")
    private String image;

    @Schema(description = "학번")
    private String studentNo;

    @Schema
    private String email;

    private int flag;

    private boolean deleted;

    public UserSearchDto(User user) {
        id = user.getId();
        name = user.getName();
        image = user.getImage();
        studentNo = user.getStudentNo();
        email = user.getEmail();
        flag = user.getFlag();
        deleted = user.isDeleted();

    }
}
