package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "성장 과정")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDto {

    @Schema(description = "성장 과정 id")
    private long progressId;

    @Schema(description = "성장 이벤트")
    private Growth growth;

    @Schema(description = "성장 이벤트 id")
    private long growthId;

    @Schema(description = "작성자")
    private User user;

    @Schema(description = "날짜")
    private ProgressDate progressDate;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "좋아요 개수")
    private int likeCnt;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public ProgressDto(Progress progress) {
        this.progressId = progress.getProgressId();
        this.growth = progress.getGrowth();
        this.growthId = progress.getGrowth().getGrowthId();
        this.user = progress.getUser();
        this.progressDate = progress.getProgressDate();
        this.content = progress.getContent();
        this.likeCnt = progress.getLikeCnt();
    }

    // 업데이트용 메소드
    public Progress updateProgress(long progressId, ProgressDto progressDto) {
        return Progress.builder()
                .progressId(progressDto.progressId)
                .growth(progressDto.growth)
                .user(progressDto.user)
                .progressDate(progressDto.progressDate)
                .content(progressDto.content)
                .likeCnt(progressDto.likeCnt).build();
    }

    // DTO -> Entity 변환
    public Progress toEntity() {
        return Progress.builder()
                .progressId(this.progressId)
                .growth(this.growth)
                .user(this.user)
                .progressDate(this.progressDate)
                .content(this.content)
                .likeCnt(this.likeCnt).build();
    }
}
