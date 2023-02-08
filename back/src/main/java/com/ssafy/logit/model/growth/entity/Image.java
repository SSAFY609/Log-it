package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.growth.dto.ImageDto;
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
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    private String imageUrl;

    // Entity -> DTO 변환
    public ImageDto toDto() {
        return ImageDto.builder()
                .imageId(this.imageId)
                .progress(this.progress)
                .imageUrl(this.imageUrl).build();
    }
}
