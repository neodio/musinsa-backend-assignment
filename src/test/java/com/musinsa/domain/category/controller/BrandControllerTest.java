package com.musinsa.domain.category.controller;

import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.category.ControllerTestSupport;
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
class BrandControllerTest extends ControllerTestSupport {

    @Test
    @Order(1)
    @DisplayName("브랜드 전체 목록 조회한다.")
    void getAllBrand() throws Exception {
        //given / when / then
        mockMvc.perform(
                        get("/api/brand")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].brandId").value(1))
                .andExpect(jsonPath("$.data[0].brandName").value("A"))
                .andExpect(jsonPath("$.data[1].brandId").value(2))
                .andExpect(jsonPath("$.data[1].brandName").value("B"))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("브랜드 단건 조회한다.")
    void getBrandByIdTest() throws Exception {
        //given
        Long brandId = 1L;

        //when / then
        mockMvc.perform(
                        get("/api/brand/{brandId}", brandId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.brandId").value(1))
                .andExpect(jsonPath("$.data.brandName").value("A"))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("신규 브랜드 등록한다.")
    void registerBrandTest() throws Exception {
        //given
        BrandDto brandDto = new BrandDto("A");

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/brand")
                        .content(objectMapper.writeValueAsString(brandDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @DisplayName("브랜드 수정한다.")
    void modifyBrandTest() throws Exception {
        //given
        BrandDto brandDto = new BrandDto(1L, "A");

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/brand")
                        .content(objectMapper.writeValueAsString(brandDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("브랜드 삭제한다.")
    void removeBrandTest() throws Exception {
        //given
        Long brandId = 1L;

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/brand/{brandId}", brandId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}