package com.musinsa.domain.brand.service;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.brand.entity.Brand;
import com.musinsa.domain.brand.repository.BrandRepository;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    /**
     * 브랜드 목록 조회
     */
    @Cacheable(value = "BRAND", key = "'ALL'")
    public List<BrandDto> getAllBrand() {
        List<Brand> brandList = brandRepository.findAll();

        return brandList.stream()
            .map(BrandDto::toDto)
            .collect(Collectors.toList());
    }

    /**
     * 브랜드 단건 조회
     */
    public BrandDto getBrandById(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new BusinessException(ExceptionCode.ERROR_CODE_1009, "브랜드"));
        return BrandDto.toDto(brand);
    }

    /**
     * 브랜드 등록/수정
     */
    @Transactional
    public BrandDto saveBrand(BrandDto brandDto) {
        Brand brand = brandRepository.save(Brand.toEntity(brandDto));
        return BrandDto.toDto(brand);
    }

    /**
     * 브랜드 삭제
     */
    @Transactional
    public ResponseResult removeBrand(Long brandId) {
        brandRepository.deleteById(brandId);
        return ResponseResult.getResponseResult(brandId, 1, "success");
    }
}
