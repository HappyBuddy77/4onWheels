package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	//find by userId
    Optional<Cart> findByCartId(Long userId);
    
    //check if user has a cart
    boolean existsByUserId(Long userId);


    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = ?1")
    Optional<Cart> findByUserIdWithItems(Long userId);
}
