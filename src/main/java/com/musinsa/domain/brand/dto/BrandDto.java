package com.musinsa.domain.brand.dto;

import com.musinsa.domain.brand.entity.Brand;
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
@Schema(description = "브랜드 DTO")
public class BrandDto {

    @Schema(description = "브랜드ID")
    private Long brandId;

    @Schema(description = "브랜드명")
    @NotEmpty(message = "브랜드명")
    private String brandName;

    public BrandDto(String brandName) {
        this.brandName = brandName;
    }

    public BrandDto(Long brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public static BrandDto toDto(Brand brand) {
        return BrandDto.builder()
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .build();
    }
}
