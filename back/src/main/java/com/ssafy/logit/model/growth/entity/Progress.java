package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.growth.dto.ProgressDate;
import com.ssafy.logit.model.growth.dto.ProgressDto;
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
public class Progress {

    @Id
    @GeneratedValue
    @Column(name = "progress_id")
    private long progressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "growth_id")
    private Growth growth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private ProgressDate progressDate;

    private String content;

    private String image;

    // Entity -> DTO 변환
    public ProgressDto toDto() {
        return ProgressDto.builder()
                .progressId(this.progressId)
                .growth(this.growth)
                .user(this.user)
                .progressDate(this.progressDate)
                .content(this.content)
                .image(this.image).build();
    }
}
