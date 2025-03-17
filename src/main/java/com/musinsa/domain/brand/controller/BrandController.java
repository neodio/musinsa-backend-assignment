package com.musinsa.domain.brand.controller;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.brand.service.BrandService;
import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseObject;
import com.musinsa.global.common.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "브랜드 관리")
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    // 브랜드 목록 조회
    // url : localhost:8080/api/brand
    @Operation(summary = "브랜드 목록 조회")
    @GetMapping("")
    public ResponseObject<List<BrandDto>> getAllCategories(){
        return ResourceConverter.toResponseObject(brandService.getAllBrand());
    }

    // 브랜드 단건 조회
    // url : localhost:8080/api/brand/{brandId}
    @Operation(summary = "브랜드 단건 조회")
    @GetMapping("/{brandId}")
    public ResponseObject<BrandDto> getBrandById(@PathVariable(name = "brandId") Long brandId) {
        return ResourceConverter.toResponseObject(brandService.getBrandById(brandId));
    }

    // 브랜드 등록
    // url : localhost:8080/api/brand
    @Operation(summary = "브랜드 등록")
    @PostMapping("")
    @ResponseBody
    public ResponseObject<BrandDto> registerBrand(@RequestBody @Valid BrandDto brandDto) {
        return ResourceConverter.toResponseObject(brandService.saveBrand(brandDto));
    }

    // 브랜드 수정
    // url : localhost:8080/api/brand
    @Operation(summary = "브랜드 수정")
    @PutMapping("")
    public ResponseObject<BrandDto> modifyBrand(@RequestBody @Valid BrandDto brandDto) {
        return ResourceConverter.toResponseObject(brandService.saveBrand(brandDto));
    }

    // 브랜드 삭제
    // uri : localhost:8080/api/brand/{brandId}
    @Operation(summary = "브랜드 삭제")
    @DeleteMapping("/{brandId}")
    public ResponseObject<ResponseResult> removeBrand(@PathVariable(name = "brandId") Long brandId) {
        return ResourceConverter.toResponseObject(brandService.removeBrand(brandId));
    }

    // 브랜드 카운트
    // uri : localhost:8080/api/brand/count
    @Operation(summary = "브랜드 카운트")
    @GetMapping("/count")
    public ResponseObject<Long> getBrandCount() {
        return ResourceConverter.toResponseObject(brandService.getBrandCount());
    }
}
