package com.musinsa.domain.product.dto;

import com.musinsa.domain.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "상품 DTO")
public class ProductDto {

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

    @Schema(description = "등록자")
    private String createdBy;

    @Schema(description = "수정자")
    private String lastModifiedBy;

    @Schema(description = "등록일시")
    private String createdDate;

    @Schema(description = "수정일시")
    private String lastModifiedDate;

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .productPrice(product.getProductPrice())
            .categoryId(product.getCategory().getCategoryId())
            .categoryName(product.getCategory().getCategoryName())
            .brandId(product.getBrand().getBrandId())
            .brandName(product.getBrand().getBrandName())
            .createdBy(product.getCreatedBy())
            .lastModifiedBy(product.getLastModifiedBy())
            .createdDate(String.valueOf(product.getCreatedDate()))
            .lastModifiedDate(String.valueOf(product.getLastModifiedDate()))
            .build();
    }
}
