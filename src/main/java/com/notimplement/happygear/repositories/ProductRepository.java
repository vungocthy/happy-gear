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

    List<Product> findTop4ByOrderByProductIdDesc();

    @Query("SELECT p FROM Product p " +
            "WHERE p.productId IN (" +
            "SELECT od.product.productId " +
            "FROM OrderDetail od " +
            "GROUP BY od.product.productId " +
            "ORDER BY COUNT(od.product.productId) DESC )"
    )
    List<Product> findTop4BestSellingProduct(Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE (:#{#brand.size()} = 0 OR p.brand.brandId IN :brand)" +
            "AND (:#{#category.size()} = 0 OR p.category.categoryId IN :category) " +
            "AND p.price BETWEEN :fromPrice AND :toPrice " +
            "AND p.productName LIKE %:search%")
    Page<Product> findProductsAndFilter(List<Integer> brand, List<Integer> category,
                                        Double fromPrice, Double toPrice, String search, Pageable pageable);
}
