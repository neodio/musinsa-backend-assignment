package com.musinsa.domain.product.dto;

import com.musinsa.domain.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "상품 set DTO")
public class ProductSetDto {

    @Schema(description = "상품ID")
    private Long productId;

    @Schema(description = "상품명")
    @NotEmpty(message = "상품명")
    private String productName;

    @Schema(description = "상품가격")
    @NotNull(message = "상품가격")
    @Min(value = 100, message = "상품가격")
    @Max(value = 100000000, message = "상품가격")
    private int productPrice;

    @Schema(description = "카테고리ID")
    private Long categoryId;

    @Schema(description = "브랜드ID")
    private Long brandId;

    public ProductSetDto(String productName, int productPrice, Long categoryId, Long brandId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public static ProductSetDto toDto(Product product) {
        return ProductSetDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
    }
}
