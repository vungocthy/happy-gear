package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductId(Integer id);

    @Query("SELECT p FROM Product p " +
            "WHERE p.brand.brandId = :brandId " +
            "AND p.category.categoryId = :categoryId " +
            "AND p.price between :fromPrice AND :toPrice")
    Page<Product> findAllProductWithFilter(Integer brandId, Integer categoryId,
                                           Double fromPrice, Double toPrice, Pageable pageable);

    List<Product> findTop4ByOrderByProductIdDesc();

    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE p.productId IN (" +
            "SELECT od.product.productId " +
            "FROM OrderDetail od " +
            "GROUP BY od.product.productId " +
            "ORDER BY COUNT(od.product.productId) DESC )"
    )
    List<Product> findTop4BestSellingProduct(Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE (:brandIds IS NULL OR p.brand.brandId IN (:brandIds))" +
            "AND (:categoryIds IS NULL OR p.category.categoryId IN (:categoryIds)) " +
            "AND p.price BETWEEN :fromPrice AND :toPrice " +
            "AND p.productName LIKE %:search%")
    Page<Product> findProductsAndFilter(List<Integer> brandIds, List<Integer> categoryIds,
                                        Double fromPrice, Double toPrice, String search, Pageable pageable);
}
