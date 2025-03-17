package com.musinsa.domain.brand.repository;


import com.musinsa.domain.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Long countBy();
}
