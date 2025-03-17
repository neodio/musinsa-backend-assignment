package com.musinsa.domain.brand.dto;

import com.musinsa.domain.brand.entity.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "브랜드 DTO")
public class BrandDto {

    @Schema(description = "브랜드ID")
    private Long brandId;

    @Schema(description = "브랜드명")
    @NotEmpty(message = "브랜드명")
    private String brandName;

    @Schema(description = "등록자")
    private String createdBy;

    @Schema(description = "수정자")
    private String lastModifiedBy;

    @Schema(description = "등록일시")
    private String createdDate;

    @Schema(description = "수정일시")
    private String lastModifiedDate;

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
                .createdBy(brand.getCreatedBy())
                .lastModifiedBy(brand.getLastModifiedBy())
                .createdDate(String.valueOf(brand.getCreatedDate()))
                .lastModifiedDate(String.valueOf(brand.getLastModifiedDate()))
                .build();
    }
}
