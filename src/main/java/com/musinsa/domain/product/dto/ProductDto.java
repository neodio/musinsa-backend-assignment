package com.musinsa.domain.product.dto;

import com.musinsa.domain.product.entity.Product;
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
@Schema(description = "상품 DTO")
public class ProductDto {

    @Schema(description = "상품ID")
    private Long productId;

    @Schema(description = "상품명")
    @NotEmpty(message = "상품명")
    private String productName;

    public ProductDto(String productName) {
        this.productName = productName;
    }

    public ProductDto(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .build();
    }
}
