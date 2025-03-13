package com.musinsa.domain.product.repository;


import com.musinsa.domain.product.dto.BrandLowestDto;
import com.musinsa.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query(value = "SELECT A.brand_id, SUM(A.min_price)"
                + "  FROM ("
                + "         SELECT p.category_id, p.brand_id, MIN(p.product_price) min_price"
                + "           FROM PRODUCT p"
                + "          GROUP BY p.category_id, p.brand_id"
                + "        ) AS A"
                + " GROUP BY A.brand_id", nativeQuery = true)
    List<BrandLowestDto> findBrandLowest();
}
