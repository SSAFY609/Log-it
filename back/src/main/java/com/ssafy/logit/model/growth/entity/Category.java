package com.ssafy.logit.model.growth.entity;

import com.ssafy.logit.model.growth.dto.CategoryDto;
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
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;

    private String categoryName;

    // Entity -> DTO 변환
    public CategoryDto toDto() {
        return CategoryDto.builder()
                .categoryId(this.categoryId)
                .categoryName(this.categoryName).build();
    }
}
