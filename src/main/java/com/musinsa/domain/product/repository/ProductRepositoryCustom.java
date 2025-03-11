package com.musinsa.domain.product.repository;

import com.musinsa.domain.product.dto.ProductDto;
import io.weverse.domain.shop.dto.GoodsCategoryDto;
import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductDto> getProductListByCategory(Long categoryId);
}
