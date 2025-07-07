package com._onWheels._onWheels;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	//find all items in a cart
    List<CartItem> findByUserId(Long cart_id);
    
    // Find item by cart and product
    CartItem findByCartAndProductId(Cart cart, String product_id);
    
    // Delete all items in a cart
    void deleteByCartId(Long cart_id);
}
