package com.musinsa.domain.category.service;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.brand.service.BrandService;
import com.musinsa.domain.category.IntegrationTestSupport;
import com.musinsa.global.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandServiceTest extends IntegrationTestSupport {

    @Autowired
    BrandService brandService;


    @Test
    @Order(1)
    @DisplayName("모든 브랜드를 조회한다.")
    void getAllCategoriesTest() {
        //given /when
        List<BrandDto> BrandDtoList = brandService.getAllBrand();

        //then
        assertThat(BrandDtoList).hasSize(9)
                .extracting("BrandId", "BrandName")
                .contains(
                        tuple(2L, "B"),
                        tuple(3L, "C")
                );
    }

    @Test
    @Order(2)
    @DisplayName("브랜드 단건 조회한다.")
    void getBrandByIdTest() {
        //given
        Long BrandId = 5L;

        //when
        BrandDto Brand = brandService.getBrandById(BrandId);

        //then
        assertThat(Brand).isNotNull();
        assertThat(Brand)
                .extracting("BrandId", "BrandName")
                .contains(5L, "E");
    }

    @Test
    @Order(3)
    @DisplayName("브랜드 단건을 조회시, 없는 ID로 조회한다.")
    void getBrandByIdErrorTest() {
        //given
        Long BrandId = 100L;

        //when / than
        assertThatThrownBy(() -> brandService.getBrandById(BrandId))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @Order(4)
    @DisplayName("신규 브랜드를 등록한다.")
    void saveBrandTest() {
        //given
        BrandDto BrandDto = new BrandDto("NEW Brand");

        //when
        BrandDto savedBrand = brandService.saveBrand(BrandDto);

        //then
        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getBrandName()).isEqualTo("NEW Brand");
    }

    @Test
    @Order(5)
    @DisplayName("기존 브랜드를 수정한다.")
    void modifyBrandTest() {
        //given
        BrandDto BrandDto = new BrandDto(1L, "NEW Brand");

        //when
        BrandDto savedBrand = brandService.saveBrand(BrandDto);

        //then
        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getBrandName()).isEqualTo("NEW Brand");
    }

    @Test
    @Order(6)
    @DisplayName("브랜드를 삭제한다.")
    void removeBrandTest() {
        //given
        Long BrandId = 5L;

        //when / than
        //외래키 참조 무결성 제약조건으로 인한 예외
        assertThrows(DataIntegrityViolationException.class, () -> {
            brandService.removeBrand(BrandId);
        });
    }

}