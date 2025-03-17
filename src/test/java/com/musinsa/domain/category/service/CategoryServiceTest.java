package com.musinsa.domain.category.service;

import com.musinsa.domain.category.IntegrationTestSupport;
import com.musinsa.domain.category.dto.CategoryDto;
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

class CategoryServiceTest extends IntegrationTestSupport {

    @Autowired
    CategoryService categoryService;


    @Test
    @Order(1)
    @DisplayName("모든 카테고리를 조회한다.")
    void getAllCategoriesTest() {
        //given /when
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        //then
        assertThat(categoryDtoList)
                .extracting("categoryId", "categoryName")
                .contains(
                        tuple(6L, "모자"),
                        tuple(7L, "양말")
                );
    }

    @Test
    @Order(2)
    @DisplayName("카테고리 단건 조회한다.")
    void getCategoryByIdTest() {
        //given
        Long categoryId = 5L;

        //when
        CategoryDto category = categoryService.getCategoryById(categoryId);

        //then
        assertThat(category).isNotNull();
        assertThat(category)
                .extracting("categoryId", "categoryName")
                .contains(5L, "가방");
    }

    @Test
    @Order(3)
    @DisplayName("카테고리 단건을 조회시, 없는 ID로 조회한다.")
    void getCategoryByIdErrorTest() {
        //given
        Long categoryId = 100L;

        //when / than
        assertThatThrownBy(() -> categoryService.getCategoryById(categoryId))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @Order(4)
    @DisplayName("신규 카테고리를 등록한다.")
    void saveCategoryTest() {
        //given
        CategoryDto categoryDto = new CategoryDto("NEW CATEGORY");

        //when
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);

        //then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getCategoryName()).isEqualTo("NEW CATEGORY");
    }

    @Test
    @Order(5)
    @DisplayName("기존 카테고리를 수정한다.")
    void modifyCategoryTest() {
        //given
        CategoryDto categoryDto = new CategoryDto(1L, "NEW CATEGORY");

        //when
        CategoryDto savedCategory = categoryService.saveCategory(categoryDto);

        //then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getCategoryName()).isEqualTo("NEW CATEGORY");
    }

    @Test
    @Order(6)
    @DisplayName("카테고리를 삭제한다.")
    void removeCategoryTest() {
        //given
        Long categoryId = 2L;

        //when / than
        //외래키 참조 무결성 제약조건으로 인한, 예외
        assertThrows(DataIntegrityViolationException.class, () -> {
            categoryService.removeCategory(categoryId);
        });
    }

}