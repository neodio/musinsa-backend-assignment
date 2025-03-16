package com.musinsa.domain.category.service;

import com.musinsa.domain.category.dto.CategoryDto;
import com.musinsa.domain.category.entity.Category;
import com.musinsa.domain.category.repository.CategoryRepository;
import com.musinsa.global.common.ResponseResult;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    /**
     * 카테고리 목록 조회
     */
//    @Cacheable(value = "CATEGORY", key = "'ALL'")
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
            .map(CategoryDto::toDto)
            .collect(Collectors.toList());
    }

    /**
     * 카테고리 단건 조회
     */
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new BusinessException(ExceptionCode.ERROR_CODE_1009, "카테고리"));
        return CategoryDto.toDto(category);
    }

    /**
     * 카테고리 등록/수정
     */
    @Transactional
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(Category.toEntity(categoryDto));
        return CategoryDto.toDto(category);
    }

    /**
     * 카테고리 삭제
     */
    @Transactional
    public ResponseResult removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return ResponseResult.getResponseResult(categoryId, 1, "success");
    }
}
