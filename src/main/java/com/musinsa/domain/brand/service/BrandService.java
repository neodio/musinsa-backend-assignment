package com.musinsa.domain.brand.service;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.brand.entity.Brand;
import com.musinsa.domain.brand.repository.BrandRepository;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        List<BrandDto> brandDtoList = new ArrayList<>();
        for (Brand brand : brandList) {
            BrandDto brandDto = new BrandDto();
            brandDto.setBrandId(brand.getBrandId());
            brandDto.setBrandName(brand.getBrandName());
            brandDtoList.add(brandDto);
        }
        return brandDtoList;
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
