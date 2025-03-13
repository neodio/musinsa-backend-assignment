package com.musinsa.domain.product.controller;

import com.musinsa.domain.product.dto.ProductByBrandTotalDto;
import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductLowestTotalDto;
import com.musinsa.domain.product.dto.ProductSetDto;
import com.musinsa.domain.product.service.ProductService;
import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseObject;
import com.musinsa.global.common.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    // 상품 목록 조회
    // url : localhost:8080/api/product
    @Operation(summary = "상품 목록 조회")
    @GetMapping("")
    public ResponseObject<List<ProductDto>> getAllProduct(){
        return ResourceConverter.toResponseObject(productService.getAllProduct());
    }

    // 상품 단건 조회
    // url : localhost:8080/api/product/{productId}
    @Operation(summary = "상품 단건 조회")
    @GetMapping("/{productId}")
    public ResponseObject<ProductDto> getProductById(@PathVariable(name = "productId") Long productId) {
        return ResourceConverter.toResponseObject(productService.getProductById(productId));
    }

    // 상품 등록
    // url : localhost:8080/api/product
    @Operation(summary = "상품 등록")
    @PostMapping("")
    @ResponseBody
    public ResponseObject<ProductSetDto> registerProduct(@RequestBody @Valid ProductSetDto productSetDto) {
        return ResourceConverter.toResponseObject(productService.saveProduct(productSetDto));
    }

    // 상품 수정
    // url : localhost:8080/api/product
    @Operation(summary = "상품 수정")
    @PutMapping("")
    public ResponseObject<ProductSetDto> modifyProduct(@RequestBody @Valid ProductSetDto productSetDto) {
        return ResourceConverter.toResponseObject(productService.saveProduct(productSetDto));
    }

    // 상품 삭제
    // uri : localhost:8080/api/product/{productId}
    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{productId}")
    public ResponseObject<ResponseResult> removeProduct(@PathVariable(name = "productId") Long productId) {
        return ResourceConverter.toResponseObject(productService.removeProduct(productId));
    }

    // 구현1) 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    // uri : localhost:8080/api/product/lowest
    @Operation(summary = "카테고리별 최저가 상품")
    @GetMapping("/lowest")
    public ResponseObject<ProductLowestTotalDto> getProductLowest() {
        return ResourceConverter.toResponseObject(productService.getProductLowest());
    }

    // 구현2) 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    // uri : localhost:8080/api/product/brandLowest
    @Operation(summary = "단일 브랜드 상품 조회")
    @GetMapping("/brandLowest")
    public ResponseObject<ProductByBrandTotalDto> brandLowest() {
        return ResourceConverter.toResponseObject(productService.brandLowest());
    }
}
