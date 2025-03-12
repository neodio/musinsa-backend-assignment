package com.musinsa.domain.product.repository;

import com.musinsa.domain.product.dto.ProductLowestDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.musinsa.domain.brand.entity.QBrand.brand;
import static com.musinsa.domain.category.entity.QCategory.category;
import static com.musinsa.domain.product.entity.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductLowestDto> getLowestProductListByCategory() {
        return queryFactory
            .select(Projections.fields(ProductLowestDto.class,
                product.productId,
                product.productName,
                product.productPrice,
                category.categoryId,
                category.categoryName,
                brand.brandId,
                brand.brandName
            ))
            .from(product)
            .innerJoin(product.category, category)
            .innerJoin(product.brand, brand)
            .where(
                Expressions.list(product.category.categoryId, product.productPrice).in(
                        JPAExpressions
                            .select(Expressions.list(product.category.categoryId, product.productPrice.min()))
                            .from(product)
                            .groupBy(product.category.categoryId))
            )
            .orderBy(product.category.categoryId.asc(), product.productId.desc())
            .fetch();
    }
}
