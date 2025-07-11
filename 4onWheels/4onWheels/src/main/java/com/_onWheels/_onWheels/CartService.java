package com._onWheels._onWheels;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	public Cart addItemToCart(Long userId, String productId, int quantity) {
		
		Cart cart;
		Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
		
		if(optionalCart.isPresent()) {
			
			cart = optionalCart.get();
		}
		else {
			cart = new Cart(userId);
			cart = cartRepository.save(cart);
		}
		
		CartItem existingItem = cartItemRepository.findByCartAndProductId(cart, productId);
		
		if(existingItem!=null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
		}
		else {
			Optional<Vehicle> vehicleOpt = vehicleRepository.findById(Long.parseLong(productId));
			if(vehicleOpt.isPresent()) {
				Vehicle vehicle = vehicleOpt.get();
				
				CartItem cartItem = new CartItem(cart,vehicle.getFullName(),productId,quantity,vehicle.getPrice());
				if (cart.getCartItems() == null) {
				    cart.setCartItems(new ArrayList<>());
				}
				cart.getCartItems().add(cartItem);
			}
			else {
			    throw new RuntimeException("Vehicle not found with ID: " + productId);

			}
			
		}
		cart = cartRepository.save(cart);
		return cart;
		}
	public Cart getCartByUserId(Long userId) {
		
	    return cartRepository.findByUserIdWithItems(userId).orElse(new Cart(userId));

}
	
	public void updateQuantity(Long cartItemId, int newQuantity) {
	
	    
	    Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
	    
	    if(cartItemOpt.isEmpty()) {
	    	throw new RuntimeException("Cart item not found with ID: " + cartItemId);
	    }
	    CartItem cartItem = cartItemOpt.get();
	    
	    if(newQuantity<0) {
	    	throw new RuntimeException("Quantity cannot be negative: " + cartItemId);
	    }
	    else if(newQuantity ==0) {
	    	
	    	cartItemRepository.deleteById(cartItemId);
	    }
	    else {
	    	cartItem.setQuantity(newQuantity);
	    	cartItemRepository.save(cartItem);
	    }
	}
	
	public void removeItem(Long cartItemId) {
		
	    cartItemRepository.deleteById(cartItemId);

	}
	
	public void clearCart(Long userId) {
		
		Cart cart;
		Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
		if(optionalCart.isPresent()) {
			cart = optionalCart.get();
			cartItemRepository.deleteByCart(cart);
		}
	}
}
