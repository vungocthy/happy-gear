package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopAddressRepository extends JpaRepository<ShopAddress, Integer> {
    @Query("SELECT sa FROM ShopAddress sa WHERE sa.product.productId = :productId")
    List<ShopAddress> findShopAddressByProductId(Integer productId);
}
