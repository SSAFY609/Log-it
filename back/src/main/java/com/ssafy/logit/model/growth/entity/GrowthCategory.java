package com.ssafy.logit.model.growth.entity;

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
public class GrowthCategory {

    @Id
    @GeneratedValue
    @Column(name = "growth_category_id")
    private Long growthCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "growth_id")
    private Growth growthEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
