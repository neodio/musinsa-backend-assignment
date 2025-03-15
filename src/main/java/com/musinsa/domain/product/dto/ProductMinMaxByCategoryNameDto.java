package com.musinsa.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "카테고리별 그룹별 최저가 조회 DTO")
public class ProductMinMaxByCategoryNameDto {

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "카테고리명")
    private String categoryName;

    @Schema(description = "최저가 상품")
    private List<ProductDto> lowestProductList;

    @Schema(description = "최고가 상품")
    private List<ProductDto> highestProductList;
}