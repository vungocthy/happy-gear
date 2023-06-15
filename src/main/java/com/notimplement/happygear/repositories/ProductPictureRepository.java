package com.notimplement.happygear.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.notimplement.happygear.entities.ProductPicture;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {

	// List<ProductPicture> findByPictureProductAndStatus(Product pictureProduct, Boolean status);

	// @Query("SELECT new ProductPicture(MIN(pp.pictureId), pp.pictureUrl, pp.status, pp.pictureProduct ) "
	// 		+ "FROM ProductPicture pp "
	// 		+ "WHERE pp.pictureProduct.productId = ?1 "
	// 		+ "GROUP BY pp.pictureProduct.productId ")
	// ProductPicture getMainByProductId(Integer id);
}
