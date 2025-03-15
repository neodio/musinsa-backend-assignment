package com.musinsa.domain.product.controller;

import com.musinsa.domain.product.dto.ProductByBrandTotalDto;
import com.musinsa.domain.product.dto.ProductLowestTotalDto;
import com.musinsa.domain.product.dto.ProductMinMaxByCategoryNameDto;
import com.musinsa.domain.product.service.ProductGetService;
import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "상품 조회 API")
@RequestMapping("/api/product")
public class ProductGetController {

    private final ProductGetService productGetService;

    // 구현1) 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    // uri : localhost:8080/api/product/lowest
    @Operation(summary = "카테고리별 최저가 상품")
    @GetMapping("/lowest")
    public ResponseObject<ProductLowestTotalDto> getProductLowest() {
        return ResourceConverter.toResponseObject(productGetService.getProductLowest());
    }

    // 구현2) 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    // uri : localhost:8080/api/product/brandLowest
    @Operation(summary = "단일 브랜드 최저가 상품 조회")
    @GetMapping("/brandLowest")
    public ResponseObject<ProductByBrandTotalDto> brandLowest() {
        return ResourceConverter.toResponseObject(productGetService.brandLowest());
    }

    // 구현3) 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    // url : localhost:8080/api/product/findProductByCategoryName?categoryName=상의
    @Operation(summary = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회")
    @GetMapping("/findProductByCategoryName")
    public ResponseObject<ProductMinMaxByCategoryNameDto> findProductByCategoryName(@RequestParam String categoryName) {
        return ResourceConverter.toResponseObject(productGetService.findProductByCategoryName(categoryName));
    }
}
