package com.musinsa.domain.category.controller;

import com.musinsa.domain.category.ControllerTestSupport;
import com.musinsa.domain.product.dto.ProductSetDto;
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
class ProductControllerTest extends ControllerTestSupport {

    @Test
    @Order(1)
    @DisplayName("상품 전체 목록 조회한다.")
    void getAllProduct() throws Exception {
        //given / when / then
        mockMvc.perform(
                        get("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].productId").value(1L))
                .andExpect(jsonPath("$.data[0].productName").value("상의1"))
                .andExpect(jsonPath("$.data[0].productPrice").value(11200))
                .andExpect(jsonPath("$.data[0].categoryId").value(1L))
                .andExpect(jsonPath("$.data[0].categoryName").value("상의"))
                .andExpect(jsonPath("$.data[0].brandId").value(1L))
                .andExpect(jsonPath("$.data[0].brandName").value("A"))
                .andExpect(jsonPath("$.data[1].productId").value(2L))
                .andExpect(jsonPath("$.data[1].productName").value("상의2"))
                .andExpect(jsonPath("$.data[1].productPrice").value(10500))
                .andExpect(jsonPath("$.data[1].categoryId").value(1L))
                .andExpect(jsonPath("$.data[1].categoryName").value("상의"))
                .andExpect(jsonPath("$.data[1].brandId").value(2L))
                .andExpect(jsonPath("$.data[1].brandName").value("B"))
        ;
    }

    @Test
    @Order(2)
    @DisplayName("상품 단건 조회한다.")
    void getProductByIdTest() throws Exception {
        //given
        Long productId = 1L;

        //when / then
        mockMvc.perform(
                        get("/api/product/{productId}", productId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.productId").value(1L))
                .andExpect(jsonPath("$.data.productName").value("상의1"))
        ;
    }

    @Test
    @Order(3)
    @DisplayName("신규 상품 등록한다.")
    void registerProductTest() throws Exception {
        //given
        ProductSetDto productSetDto = new ProductSetDto("상의1", 11200, 1L, 1L);

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .content(objectMapper.writeValueAsString(productSetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("상품 수정한다.")
    void modifyProductTest() throws Exception {
        //given
        ProductSetDto productSetDto = new ProductSetDto(1L, "상의1", 11200, 1L, 1L);

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product")
                        .content(objectMapper.writeValueAsString(productSetDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("상품 삭제한다.")
    void removeProductTest() throws Exception {
        //given
        Long productId = 1L;

        //when / then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}