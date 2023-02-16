package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.growth.dto.GrowthDto;
import com.ssafy.logit.model.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Growth {

    @Id
    @GeneratedValue
    @Column(name = "growth_id")
    private long growthId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private EventDate eventDate;

    private String category;

    private boolean result;

    // Entity -> DTO 변환
    public GrowthDto toDto() {
        return GrowthDto.builder()
                .growthId(this.growthId)
                .user(this.user)
                .eventDate(this.eventDate)
                .category(this.category)
                .result(this.result).build();
    }
}
