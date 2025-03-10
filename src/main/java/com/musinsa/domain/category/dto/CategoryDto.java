package com.musinsa.domain.category.dto;

import com.musinsa.domain.category.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Schema(description = "카테고리 DTO")
public class CategoryDto {

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "카테고리명")
    @NotEmpty(message = "카테고리명")
    private String categoryName;

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDto(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
