package com.musinsa.domain.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.domain.admin.controller.PageController;
import com.musinsa.domain.admin.service.AdminMenuService;
import com.musinsa.domain.brand.controller.BrandController;
import com.musinsa.domain.brand.dto.BrandDto;
import com.musinsa.domain.brand.service.BrandService;
import com.musinsa.domain.category.controller.CategoryController;
import com.musinsa.domain.category.dto.CategoryDto;
import com.musinsa.domain.category.service.CategoryService;
import com.musinsa.domain.product.controller.ProductController;
import com.musinsa.domain.product.controller.ProductGetController;
import com.musinsa.domain.product.dto.ProductDto;
import com.musinsa.domain.product.service.ProductGetService;
import com.musinsa.domain.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(controllers = {CategoryController.class, BrandController.class, ProductController.class, PageController.class, ProductGetController.class})
public abstract class ControllerTestSupport {


    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected CategoryService categoryService;

    @MockitoBean
    protected BrandService brandService;

    @MockitoBean
    protected ProductService productService;

    @MockitoBean
    protected AdminMenuService adminMenuService;

    @MockitoBean
    protected ProductGetService productGetService;

    @BeforeEach
    void initEach() {
        this.mockMvc = standaloneSetup(new CategoryController(categoryService),
                                        new BrandController(brandService),
                                        new ProductController(productService),
                                        new PageController(adminMenuService),
                                        new ProductGetController(productGetService))
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .defaultRequest(get("/")
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                )
                .alwaysExpect(status().isOk())
                .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .alwaysDo(print())
                .build();

        List<CategoryDto> categoryDtoList = new ArrayList<>();
        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryId(1L);
        categoryDto1.setCategoryName("상의");

        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setCategoryId(2L);
        categoryDto2.setCategoryName("아우터");

        categoryDtoList.add(categoryDto1);
        categoryDtoList.add(categoryDto2);

        List<BrandDto> brandDtoList = new ArrayList<>();
        BrandDto brandDto1 = new BrandDto();
        brandDto1.setBrandId(1L);
        brandDto1.setBrandName("A");

        BrandDto brandDto2 = new BrandDto();
        brandDto2.setBrandId(2L);
        brandDto2.setBrandName("B");

        brandDtoList.add(brandDto1);
        brandDtoList.add(brandDto2);

        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId(1L);
        productDto1.setProductName("상의1");
        productDto1.setProductPrice(11200);
        productDto1.setCategoryId(1L);
        productDto1.setCategoryName("상의");
        productDto1.setBrandId(1L);
        productDto1.setBrandName("A");

        ProductDto productDto2 = new ProductDto();
        productDto2.setProductId(2L);
        productDto2.setProductName("상의2");
        productDto2.setProductPrice(10500);
        productDto2.setCategoryId(1L);
        productDto2.setCategoryName("상의");
        productDto2.setBrandId(2L);
        productDto2.setBrandName("B");

        productDtoList.add(productDto1);
        productDtoList.add(productDto2);

        when(categoryService.getAllCategories()).thenReturn(categoryDtoList);
        when(categoryService.getCategoryById(1L)).thenReturn(categoryDto1);
        when(brandService.getAllBrand()).thenReturn(brandDtoList);
        when(brandService.getBrandById(1L)).thenReturn(brandDto1);
        when(productService.getAllProduct()).thenReturn(productDtoList);
        when(productService.getProductById(1L)).thenReturn(productDto1);
    }
}
