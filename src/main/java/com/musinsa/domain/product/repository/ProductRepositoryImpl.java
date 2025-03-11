package com.musinsa.domain.product.repository;

import com.musinsa.domain.product.dto.ProductDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductDto> getProductListByCategory(Long categoryId) {
        return queryFactory
            .select(Projections.fields(ProductDto.class,
                product.productId,
                product.productName,
                category.categoryId,
                category.categoryName
            ))
            .from(product)
            .innerJoin(product.categories, productCategory)
            .where(category.categoryId.eq(categoryId))
            .fetch();
    }
}
