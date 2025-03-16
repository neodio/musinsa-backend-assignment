package com.musinsa.domain.brand.entity;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue
    private Long brandId;

    private String brandName;

    public Brand(Long brandId) {
        this.brandId = brandId;
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public static Brand toEntity(BrandDto brandDto) {
        return Brand.builder()
                .brandId(brandDto.getBrandId())
                .brandName(brandDto.getBrandName())
                .build();
    }
}
