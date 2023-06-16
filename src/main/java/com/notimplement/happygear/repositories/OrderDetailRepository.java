package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    OrderDetail findByDetailId(Integer id);
    @Query("SELECT o FROM OrderDetail o WHERE o.order.orderId = ?1")
    List<OrderDetail> findAllByOrderId(Integer id);
    @Query("SELECT o FROM OrderDetail o WHERE o.product.productId = :id")
    List<OrderDetail> findAllByProductId(Integer id);
}
