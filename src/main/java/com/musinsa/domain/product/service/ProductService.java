package com.musinsa.domain.product.service;

import com.musinsa.domain.product.dto.ProductByBrandDto;
import com.musinsa.domain.product.dto.ProductByBrandTotalDto;
import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductLowestDto;
import com.musinsa.domain.product.dto.ProductLowestTotalDto;
import com.musinsa.domain.product.dto.ProductSetDto;
import com.musinsa.domain.product.entity.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    /**
     * 상품 목록 조회
     */
    @Cacheable(value = "PRODUCT", key = "'ALL'")
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
     * 카테고리별 최저가 상품
     */
    public ProductLowestTotalDto getProductLowest() {
        List<ProductLowestDto> lowestProductList = productRepository.getLowestProductListByCategory();
        return ProductLowestTotalDto.builder()
                .lowestList(lowestProductList.stream()
                            .filter(distinctByCategory(ProductLowestDto::getCategoryId))
                            .collect(Collectors.toList()))
                .totalPrice(lowestProductList.stream()
                            .mapToInt(ProductLowestDto::getProductPrice)
                            .sum())
                .build();
    }

    /**
     * 단일 브랜드 상품 조회
     */
    public ProductByBrandTotalDto findProductByBrandId(Long brandId) {
        List<ProductByBrandDto> productByBrandList = productRepository.findProductByBrandId(brandId);

        if(ObjectUtils.isEmpty(productByBrandList)) {
            throw new BusinessException(ExceptionCode.ERROR_CODE_1009, "브랜드 상품");
        }

        return ProductByBrandTotalDto.builder()
            .brandId(productByBrandList.get(0).getBrandId())
            .brandName(productByBrandList.get(0).getBrandName())
            .productList(new ArrayList<>(productByBrandList))
            .totalPrice(productByBrandList.stream()
                        .mapToInt(ProductByBrandDto::getProductPrice)
                        .sum())
            .build();
    }

    /**
     * 중복 제거
     */
    private <T> Predicate<T> distinctByCategory(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
