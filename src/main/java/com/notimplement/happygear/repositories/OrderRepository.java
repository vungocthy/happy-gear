package com.notimplement.happygear.repositories;

import com.notimplement.happygear.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByOrderId(Integer id);
    @Query("SELECT o FROM Order o WHERE o.user.username = :username")
    List<Order> findByUserName(String username);
}

