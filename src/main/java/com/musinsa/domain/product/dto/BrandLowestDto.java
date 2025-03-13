package com.musinsa.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "단일 브랜드 상품 조회 DTO")
public interface BrandLowestDto {

    @Schema(description = "브랜드ID")
    Long getBrandId = 0L;

    @Schema(description = "최저가격의 합계")
    Integer getLowestTotalPrice = 0;
}
