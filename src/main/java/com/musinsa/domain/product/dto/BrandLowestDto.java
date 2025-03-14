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
@Schema(description = "카테고리별 그룹별 최저가 조회 DTO")
public class BrandLowestDto {

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "브랜드ID")
    private Long brandId;

    @Schema(description = "최저가격의 합계")
    private Integer lowestPrice;
}