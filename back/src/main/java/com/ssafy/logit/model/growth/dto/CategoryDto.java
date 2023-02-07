package com.ssafy.logit.model.growth.dto;

import com.ssafy.logit.model.growth.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "성장 이벤트 카테고리")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Schema(description = "카테고리 id")
    private Long categoryId;

    @Schema(description = "카테고리 이름")
    private String categoryName;

    // 생성자 (List<Entity> -> List<DTO> 변환을 위함)
    public CategoryDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }

    // 업데이트용 메소드
    public Category updateCategory(long categoryId, CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.categoryId)
                .categoryName(categoryDto.categoryName).build();
    }

    // DTO -> Entity 변환
    public Category toEntity() {
        return Category.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName).build();
    }
}
