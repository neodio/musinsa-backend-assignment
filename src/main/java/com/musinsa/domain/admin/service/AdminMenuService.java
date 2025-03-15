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
                AdminMenuDto.builder().title("상품 관리").url("/pages/product").code("product").build()
        );
    }
}
