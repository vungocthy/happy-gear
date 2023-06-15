package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    Product findByProductId(Integer id);

    // @Query("SELECT p FROM Product p WHERE p.brand.brandId = ?1 " +
    //         "AND p.proCategory.categoryId = ?2 AND p.price between ?3 AND ?4")
    // Page<Product> findAllProductWithFilter(Integer brandId, Integer categoryId,
    //                                        Double fromPrice, Double toPrice, Pageable pageable);

    // @Query(value = "SELECT p FROM Product p ORDER BY p.quantity ASC LIMIT 4")
    // List<Product> findTop5AndOrderByQuantityAsc();

    // @Query(value = "SELECT p.* FROM Product p ORDER BY p.productId DESC LIMIT 4")
    // List<Product> findLatestProduct();

    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
    
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
    
}
