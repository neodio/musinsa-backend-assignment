package com.musinsa.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "단일 브랜드 상품 조회 DTO")
public class ProductByBrandDto {

    @Schema(description = "상품ID")
    private Long productId;

    @Schema(description = "상품명")
    private String productName;

    @Schema(description = "상품가격")
    private int productPrice;

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "카테고리명")
    private String categoryName;

    @Schema(description = "브랜드ID")
    private Long brandId;

    @Schema(description = "브랜드명")
    private String brandName;
}
