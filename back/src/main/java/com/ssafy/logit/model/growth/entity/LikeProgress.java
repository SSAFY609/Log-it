package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.growth.dto.LikeProgressDto;
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
public class LikeProgress {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    // Entity -> DTO 변환
    public LikeProgressDto toDto() {
        return LikeProgressDto.builder()
                .likeId(this.likeId)
                .progress(this.progress)
                .user(this.user).build();
    }
}
