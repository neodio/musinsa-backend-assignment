package com.musinsa.domain.product.entity;

import com.musinsa.domain.brand.entity.Brand;
import com.musinsa.domain.category.entity.Category;
import com.musinsa.domain.product.dto.ProductSetDto;
import com.musinsa.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    private Long productId;

    private String productName;

    private int productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product(String productName, int productPrice, Category category, Brand brand) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
        this.brand = brand;
    }

    public static Product toEntity(ProductSetDto productSetDto) {
        return Product.builder()
                .productId(productSetDto.getProductId())
                .productName(productSetDto.getProductName())
                .build();
    }
}
