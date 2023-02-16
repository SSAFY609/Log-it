package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.growth.dto.GrowthUserDto;
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
public class GrowthUser {

    @Id
    @GeneratedValue
    @Column(name = "growth_user_id")
    private long growthUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "growth_id")
    private Growth growth;

    private boolean type;

    // Entity -> DTO 변환
    public GrowthUserDto toDto() {
        return GrowthUserDto.builder()
                .growthUserId(this.growthUserId)
                .user(this.user)
                .growth(this.growth)
                .type(this.type).build();
    }
}
