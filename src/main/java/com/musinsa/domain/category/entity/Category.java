package com.musinsa.domain.category.entity;

import com.musinsa.domain.category.dto.CategoryDto;
import com.musinsa.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    private Long categoryId;

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryName(categoryDto.getCategoryName())
                .build();
    }
}
