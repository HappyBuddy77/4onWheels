package com._onWheels._onWheels;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	//find all items in a cart
    List<CartItem> findByCart(Cart cart);
    
    // Find item by cart and product
    CartItem findByCartAndProductId(Cart cart, String productId);
    
    // Delete all items in a cart
    void deleteByCart(Cart cart);
}
