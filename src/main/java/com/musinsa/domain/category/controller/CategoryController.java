package com.musinsa.domain.category.controller;

import com.musinsa.domain.category.dto.CategoryDto;
import com.musinsa.domain.category.service.CategoryService;
import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseObject;
import com.musinsa.global.common.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "카테고리 관리")
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    // url : localhost:8080/api/category
    @Operation(summary = "카테고리 목록 조회")
    @GetMapping("")
    public ResponseObject<List<CategoryDto>> getAllCategories(){
        return ResourceConverter.toResponseObject(categoryService.getAllCategories());
    }

    // 카테고리 단건 조회
    // url : localhost:8080/api/category/{categoryId}
    @Operation(summary = "카테고리 단건 조회")
    @GetMapping("/{categoryId}")
    public ResponseObject<CategoryDto> getCategoryById(@PathVariable(name = "categoryId") Long categoryId) {
        return ResourceConverter.toResponseObject(categoryService.getCategoryById(categoryId));
    }

    // 카테고리 등록
    // url : localhost:8080/api/category
    @Operation(summary = "카테고리 등록")
    @PostMapping("")
    @ResponseBody
    public ResponseObject<CategoryDto> registerCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ResourceConverter.toResponseObject(categoryService.saveCategory(categoryDto));
    }

    // 카테고리 수정
    // url : localhost:8080/api/category
    @Operation(summary = "카테고리 수정")
    @PutMapping("")
    public ResponseObject<CategoryDto> modifyCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ResourceConverter.toResponseObject(categoryService.saveCategory(categoryDto));
    }

    // 카테고리 삭제
    // uri : localhost:8080/api/category/{categoryId}
    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public ResponseObject<ResponseResult> removeBrand(@PathVariable(name = "categoryId") Long categoryId) {
        return ResourceConverter.toResponseObject(categoryService.removeCategory(categoryId));
    }

    // 카테고리 카운트
    // uri : localhost:8080/api/category/count
    @Operation(summary = "카테고리 카운트")
    @GetMapping("/count")
    public ResponseObject<Integer> getCategoryCount() {
        return ResourceConverter.toResponseObject(categoryService.getCategoryCount());
    }
}
