package com.musinsa.domain.category.dto;

import com.musinsa.domain.category.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카테고리 DTO")
public class CategoryDto {

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "카테고리명")
    @NotEmpty(message = "카테고리명")
    private String categoryName;

    @Schema(description = "등록자")
    private String createdBy;

    @Schema(description = "수정자")
    private String lastModifiedBy;

    @Schema(description = "등록일시")
    private String createdDate;

    @Schema(description = "수정일시")
    private String lastModifiedDate;

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .createdBy(category.getCreatedBy())
                .lastModifiedBy(category.getLastModifiedBy())
                .createdDate(category.getCreatedDate().toString())
                .lastModifiedDate(category.getLastModifiedDate().toString())
                .build();
    }
}
