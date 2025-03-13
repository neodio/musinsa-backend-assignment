package com.musinsa.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "단일 브랜드 상품 조회 총액 DTO")
public class ProductByBrandTotalDto {

    @Schema(description = "브랜드ID")
    private Long brandId;

    @Schema(description = "브랜드명")
    private String brandName;

    @Schema(description = "카테고리 최저 가격")
    private List<ProductByBrandDto> productList;

    @Schema(description = "총액")
    private Integer totalPrice;
}
