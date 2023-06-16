package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    Product findByProductId(Integer id);
    @Query("SELECT p FROM Product p WHERE p.brand.brandId = :brandId " +
            "AND p.category.categoryId = :categoryId AND p.price between :fromPrice AND :toPrice")
    Page<Product> findAllProductWithFilter(Integer brandId, Integer categoryId,
                                        Double fromPrice, Double toPrice, Pageable pageable);

    List<Product> findTop4ByOrderByProductId();
    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
}
