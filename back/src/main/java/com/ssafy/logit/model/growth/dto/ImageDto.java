package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.growth.entity.Image;
import com.ssafy.logit.model.growth.entity.Progress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "성장 과정 첨부 이미지")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    @Schema(description = "이미지 아이디")
    private long imageId;

    @Schema(description = "성장 과정")
    private Progress progress;

    @Schema(description = "이미지 url")
    private String imageUrl;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public ImageDto(Image image) {
        this.imageId = image.getImageId();
        this.progress = image.getProgress();
        this.imageUrl = image.getImageUrl();
    }

    // 업데이트용 메소드
    public Image updateImage(long imageId, ImageDto imageDto) {
        return Image.builder()
                .imageId(imageId)
                .progress(imageDto.progress)
                .imageUrl(imageDto.imageUrl).build();
    }

    // DTO -> Entity 변환
    public Image toEntity() {
        return Image.builder()
                .imageId(this.imageId)
                .progress(this.progress)
                .imageUrl(this.imageUrl).build();
    }
}
