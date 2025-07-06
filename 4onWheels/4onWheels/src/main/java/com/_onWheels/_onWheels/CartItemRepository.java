package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	//find all items in a cart
    List<CartItem> findByUserId(Long cartId);
    
    // Find item by cart and product
    CartItem findByCartAndProductId(Cart cart, String productId);
    
    // Delete all items in a cart
    void deleteByCartId(Long cartId);
}
