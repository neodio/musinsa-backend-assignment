package com.musinsa.domain.category.service;

import com.musinsa.domain.category.IntegrationTestSupport;
import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.dto.ProductSetDto;
import com.musinsa.domain.product.service.ProductService;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    ProductService productService;

    @Test
    @Order(1)
    @DisplayName("모든 상품를 조회한다.")
    void getAllCategoriesTest() {
        //given /when
        List<ProductDto> ProductDtoList = productService.getAllProduct();

        //then
        assertThat(ProductDtoList).hasSize(72)
                .extracting("ProductId", "ProductName", "ProductPrice", "CategoryId", "BrandId")
                .contains(
                        tuple(1L, "상의1", 11200, 1L, 1L),
                        tuple(2L, "상의2", 10500, 1L, 2L)
                );
    }

    @Test
    @Order(2)
    @DisplayName("상품 단건 조회한다.")
    void getProductByIdTest() {
        //given
        Long ProductId = 5L;

        //when
        ProductDto Product = productService.getProductById(ProductId);

        //then
        assertThat(Product).isNotNull();
        assertThat(Product)
                .extracting("ProductId", "ProductName")
                .contains(5L, "상의5");
    }

    @Test
    @Order(3)
    @DisplayName("상품 단건을 조회시, 없는 ID로 조회한다.")
    void getProductByIdErrorTest() {
        //given
        Long ProductId = 100L;

        //when / than
        assertThatThrownBy(() -> productService.getProductById(ProductId))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @Order(4)
    @DisplayName("신규 상품를 등록한다.")
    void saveProductTest() {
        //given
        ProductSetDto productSetDto = new ProductSetDto("상의10", 11600, 1L, 1L);

        //when
        ProductSetDto savedProduct = productService.saveProduct(productSetDto);

        //then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductName()).isEqualTo("상의10");
    }

    @Test
    @Order(5)
    @DisplayName("기존 상품를 수정한다.")
    void modifyProductTest() {
        //given
        ProductSetDto productSetDto = new ProductSetDto(12L, "상의11", 11200, 1L, 1L);

        //when
        ProductSetDto savedProduct = productService.saveProduct(productSetDto);

        //then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductName()).isEqualTo("상의11");
    }

    @Test
    @Order(6)
    @DisplayName("상품를 삭제한다.")
    void removeProductTest() {
        //given
        Long productId = 72L;

        //when
        ResponseResult responseResult = productService.removeProduct(productId);

        //then
        assertThat(responseResult.getReturnCnt()).isEqualTo(1);
        assertThat(responseResult.getReturnKey()).isEqualTo("72");
    }

}