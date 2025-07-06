package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUserId(Long userId);
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    List<Order> findByOrderStatus(String orderStatus);
    
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = ?1")
    Optional<Order> findByIdWithItems(Long orderId);
    
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.userId = ?1 ORDER BY o.createdAt DESC")
    List<Order> findByUserIdWithItems(Long userId);
}