package com.musinsa.domain.product.service;

import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductSetDto;
import com.musinsa.domain.product.entity.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    /**
     * 상품 목록 조회
     */
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
            .map(ProductDto::toDto)
            .collect(Collectors.toList());
    }

    /**
     * 상품 단건 조회
     */
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new BusinessException(ExceptionCode.ERROR_CODE_1009, "상품"));
        return ProductDto.toDto(product);
    }

    /**
     * 상품 등록/수정
     */
    @Transactional
    public ProductSetDto saveProduct(ProductSetDto productSetDto) {
        Product product = productRepository.save(Product.toEntity(productSetDto));
        return ProductSetDto.toDto(product);
    }

    /**
     * 상품 삭제
     */
    @Transactional
    public ResponseResult removeProduct(Long categoryId) {
        productRepository.deleteById(categoryId);
        return ResponseResult.getResponseResult(categoryId, 1, "success");
    }

    /**
     * 상품 카운트
     */
    @Cacheable(value = "PRODUCT", key = "'ALL'")
    public Integer getProductCount() {
        return productRepository.countBy().intValue();
    }
}
