package com.musinsa.domain.product.controller;

import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.service.ProductService;
import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public ResponseObject<List<ProductDto>> getAllCategories(){
        return ResourceConverter.toResponseObject(productService.getAllCategories());
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
    public ResponseObject<ProductDto> registerProduct(@RequestBody @Valid ProductDto productDto) {
        return ResourceConverter.toResponseObject(productService.saveProduct(productDto));
    }

    // 상품 수정
    // url : localhost:8080/api/product
    @Operation(summary = "상품 수정")
    @PutMapping("")
    public ResponseObject<ProductDto> modifyProduct(@RequestBody @Valid ProductDto productDto) {
        return ResourceConverter.toResponseObject(productService.saveProduct(productDto));
    }
}
