package com.musinsa.domain.category.controller;

import com.musinsa.domain.category.ControllerTestSupport;
import com.musinsa.domain.category.dto.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CategoryControllerTest extends ControllerTestSupport {

    @Test
    @Order(1)
    @DisplayName("카테고리 전체 목록 조회한다.")
    void getAllCategoriesTest() throws Exception {
        //given / when / then
        mockMvc.perform(
                        get("/api/category")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].categoryId").value(1))
                .andExpect(jsonPath("$.data[0].categoryName").value("상의"))
                .andExpect(jsonPath("$.data[1].categoryId").value(2))
                .andExpect(jsonPath("$.data[1].categoryName").value("아우터"))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("카테고리 단건 조회한다.")
    void getCategoryByIdTest() throws Exception {
        //given
        Long categoryId = 1L;

        //when / then
        mockMvc.perform(
                        get("/api/category/{categoryId}", categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.categoryId").value(1))
                .andExpect(jsonPath("$.data.categoryName").value("상의"))
        ;
    }

    @Test
    @Order(3)
    @DisplayName("신규 카테고리 등록한다.")
    void registerCategoryTest() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto("상의");

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/category")
                        .content(objectMapper.writeValueAsString(categoryDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("카테고리 수정한다.")
    void modifyCategoryTest() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto(1L, "상의");

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/category")
                        .content(objectMapper.writeValueAsString(categoryDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("카테고리 삭제한다.")
    void removeCategoryTest() throws Exception {
        //given
        Long categoryId = 1L;

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/category/{categoryId}", categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}