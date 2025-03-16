package com.musinsa.domain.admin.service;

import com.musinsa.domain.admin.dto.AdminMenuDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenuDto> getAdminMenu(){
        return Arrays.asList(
                AdminMenuDto.builder().title("카테고리 관리").url("/pages/category").code("category").build(),
                AdminMenuDto.builder().title("브랜드 관리").url("/pages/brand").code("brand").build(),
                AdminMenuDto.builder().title("상품 관리").url("/pages/product").code("product").build(),
                AdminMenuDto.builder().title("카테고리별 최저가 상품").url("/pages/categoryLowest").code("product").build(),
                AdminMenuDto.builder().title("브랜드 최저가 상품 조회").url("/pages/brandLowest").code("product").build(),
                AdminMenuDto.builder().title("최저, 최고 가격 브랜드와 상품").url("/pages/minMaxProduct").code("product").build()
        );
    }
}
