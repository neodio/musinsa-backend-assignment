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
@Schema(description = "카테고리별 최저가 상품 DTO")
public class ProductLowestTotalDto {

    @Schema(description = "카테고리 최저 가격")
    private List<ProductLowestDto> lowestList;

    @Schema(description = "총액")
    private Integer totalPrice;
}
