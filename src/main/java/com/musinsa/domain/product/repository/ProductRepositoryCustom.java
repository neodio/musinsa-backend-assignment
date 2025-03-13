package com.musinsa.domain.product.repository;

import com.musinsa.domain.product.dto.ProductByBrandDto;
import com.musinsa.domain.product.dto.ProductLowestDto;
import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductLowestDto> getLowestProductListByCategory();

//    BrandLowestDto findBrandLowest();

    List<ProductByBrandDto> findProductByBrandId(Long brandId);
}
