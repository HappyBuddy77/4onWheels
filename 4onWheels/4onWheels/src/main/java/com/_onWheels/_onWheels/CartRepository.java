package com._onWheels._onWheels;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	//find by userId
    Optional<Cart> findByUserId(Long userid);
    
    //check if user has a cart
    boolean existsByUserId(Long userid);


    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.userId = ?1")
    Optional<Cart> findByUserIdWithItems(Long userid);
}
