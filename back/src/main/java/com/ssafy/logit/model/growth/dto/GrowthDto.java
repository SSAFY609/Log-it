package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.growth.entity.Category;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import java.util.List;

@Schema(description = "성장 이벤트")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GrowthDto {

    @Schema(description = "아이디 (auto_increment)")
    private long growthId;

    @Schema(description = "작성자")
    private User user;

    @Schema(description = "시작, 종료 날짜")
    @Embedded
    private EventDate eventDate;

    @Schema(description = "카테고리")
    private Category category;

    @Schema(description = "카테고리 이름")
    private String categoryName;

    @Schema(description = "결과")
    private boolean result;

    @Schema(description = "공유 회원 리스트")
    private List<Long> userList;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public GrowthDto(Growth growth) {
        this.growthId = growth.getGrowthId();
        this.user = growth.getUser();
        this.eventDate = growth.getEventDate();
        this.category= growth.getCategory();
        this.result = growth.isResult();
    }

    // 업데이트용 메소드
    public Growth updateGrowthEvent(long growthId, GrowthDto growthDto) {
        return Growth.builder()
                .growthId(growthId)
                .user(growthDto.user)
                .eventDate(growthDto.eventDate)
                .category(growthDto.category)
                .result(growthDto.result).build();
    }

    // DTO -> Entity 변환
    public Growth toEntity() {
        return Growth.builder()
                .growthId(this.growthId)
                .user(this.user)
                .eventDate(this.eventDate)
                .category(this.category)
                .result(this.result).build();
    }
}
