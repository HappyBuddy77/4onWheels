package com._onWheels._onWheels;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	//find by userId
    Optional<Cart> findByCartId(Long user_id);
    
    //check if user has a cart
    boolean existsByUserId(Long user_id);


    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = ?1")
    Optional<Cart> findByUserIdWithItems(Long user_id);
}
