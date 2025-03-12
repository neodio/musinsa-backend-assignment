package com.musinsa.domain.product.service;

import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductLowestDto;
import com.musinsa.domain.product.entity.Product;
import com.musinsa.domain.product.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository productRepository;


    /**
     * 상품 목록 조회
     */
    @Cacheable(value = "PRODUCT", key = "'ALL'")
    public List<ProductDto> getAllCategories() {
        List<Product> productList = productRepository.findAll();

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDtoList.add(productDto);
        }
        return productDtoList;
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
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = productRepository.save(Product.toEntity(productDto));
        return ProductDto.toDto(product);
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
     * 카테고리별 최저가 상품
     */
    public List<ProductLowestDto> getProductLowest() {
        return productRepository.getLowestProductListByCategory();
    }
}
