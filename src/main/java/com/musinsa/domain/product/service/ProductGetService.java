package com.musinsa.domain.product.service;

import com.musinsa.domain.product.dto.BrandLowestDto;
import com.musinsa.domain.product.dto.ProductByBrandDto;
import com.musinsa.domain.product.dto.ProductByBrandTotalDto;
import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductLowestDto;
import com.musinsa.domain.product.dto.ProductLowestTotalDto;
import com.musinsa.domain.product.dto.ProductMinMaxByCategoryNameDto;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductGetService {

    private final ProductRepository productRepository;

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
     * 단일 브랜드 최저가 상품 리스트 조회
     */
    public ProductByBrandTotalDto brandLowest() {
        //카테고리별 브랜드별 최저가의 합계 조회
        List<BrandLowestDto> brandLowest = productRepository.findBrandLowest();

        //브랜드별 합계
        Map<Long, Integer> brandSumMap = brandLowest.stream()
            .collect(
                Collectors.groupingBy(
                    BrandLowestDto :: getBrandId,
                    Collectors.summingInt(BrandLowestDto :: getLowestPrice)
                )
            );

        //최저가 brandId 추출
        Long brandId = Collections.min(brandSumMap.entrySet(), Comparator.comparingInt(Entry::getValue)).getKey();

        // 브랜드의 카테고리별 최저가 상품 조회
        List<ProductByBrandDto> productByBrandList = productRepository.findProductByBrandId(brandId);
        if(ObjectUtils.isEmpty(productByBrandList)) {
            throw new BusinessException(ExceptionCode.ERROR_CODE_1009, "브랜드의 카테고리별 최저가 상품");
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
     * 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회
     */
    public ProductMinMaxByCategoryNameDto findProductByCategoryName(String categoryName) {
        //최저가 상품 조회
        List<ProductDto> lowestProduct = productRepository.findLowestByCategoryName(categoryName);
        if(ObjectUtils.isEmpty(lowestProduct)) {
            throw new BusinessException(ExceptionCode.ERROR_CODE_1009, "최저가 상품");
        }

        //최고가 상품 조회
        List<ProductDto> highestProduct = productRepository.findHighestByCategoryName(categoryName);
        if(ObjectUtils.isEmpty(lowestProduct)) {
            throw new BusinessException(ExceptionCode.ERROR_CODE_1009, "최고가 상품");
        }

        return ProductMinMaxByCategoryNameDto.builder()
                .categoryId(lowestProduct.get(0).getCategoryId())
                .categoryName(lowestProduct.get(0).getCategoryName())
                .lowestProductList(lowestProduct)
                .highestProductList(highestProduct)
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
