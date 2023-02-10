package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.growth.entity.LikeProgress;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "좋아요 관리")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LikeProgressDto {

    @Schema(description = "아이디 (auto_increment)")
    private long likeId;

    @Schema(description = "성장 과정")
    private Progress progress;

    @Schema(description = "해당 좋아요를 클릭한 회원")
    private User user;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public LikeProgressDto(LikeProgress likeProgress) {
        this.likeId = likeProgress.getLikeId();
        this.progress = likeProgress.getProgress();
        this.user = likeProgress.getUser();
    }

    // 업데이트용 메소드
    public LikeProgress updateLike(long likeId, LikeProgressDto likeProgressDto) {
        return LikeProgress.builder()
                .likeId(likeId)
                .progress(likeProgressDto.progress)
                .user(likeProgressDto.user).build();
    }

    // DTO -> Entity 변환
    public LikeProgress toEnity() {
        return LikeProgress.builder()
                .likeId(this.likeId)
                .progress(this.progress)
                .user(this.user).build();
    }
}
