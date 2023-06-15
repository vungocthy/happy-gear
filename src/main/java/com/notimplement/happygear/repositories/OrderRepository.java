package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOrderId(Integer id);

    // @Query("SELECT o FROM order o WHERE o.orderUser.username = ?1")
    // List<Order> findByUserName(String username);
}
