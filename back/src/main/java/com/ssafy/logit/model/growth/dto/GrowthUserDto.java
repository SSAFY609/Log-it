package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.GrowthUser;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "공유된 회원")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GrowthUserDto {

    @Schema(description = "아이디 (auto_increment)")
    private long growthUserId;

    @Schema(description = "공유된 회원")
    private User user;

    @Schema(description = "성장 이벤트 id")
    private Growth growth;

    @Schema(description = "초대에 응답했는지 여부")
    private boolean type;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public GrowthUserDto(GrowthUser growthUser) {
        this.growthUserId = growthUser.getGrowthUserId();
        this.user = growthUser.getUser();
        this.growth = growthUser.getGrowth();
        this.type = growthUser.isType();
    }

    // 업데이트용 메소드
    public GrowthUser updateGrowthUser(long growthUserId, GrowthUserDto growthUserDto) {
        return GrowthUser.builder()
                .growthUserId(growthUserId)
                .user(growthUserDto.user)
                .growth(growthUserDto.growth)
                .type(growthUserDto.type).build();
    }

    // DTO -> Entity 변환
    public GrowthUser toEntity() {
        return GrowthUser.builder()
                .growthUserId(this.growthUserId)
                .user(this.user)
                .growth(this.growth)
                .type(this.type).build();
    }
}
