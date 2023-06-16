package com.notimplement.happygear.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.notimplement.happygear.entities.ProductPicture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {
    @Query("SELECT p FROM ProductPicture p WHERE p.product.productId = :id")
    List<ProductPicture> findByProductId(Integer id);
}
