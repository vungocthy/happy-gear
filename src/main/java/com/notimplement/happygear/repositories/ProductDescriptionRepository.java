package com.notimplement.happygear.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.notimplement.happygear.entities.ProductDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Integer> {
    @Query("SELECT p FROM ProductDescription p WHERE p.product.productId = :id")
    ProductDescription findProductDescriptionByProductId(Integer id);
}
