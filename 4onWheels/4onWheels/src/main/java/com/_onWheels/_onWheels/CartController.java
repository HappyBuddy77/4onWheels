package com._onWheels._onWheels;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller

public class CartController {

	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/cart")
	public String viewAllCart(Principal principal,Model model) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		Cart cart = cartService.getCartByUserId(user.getId());
		
		model.addAttribute("cart",cart);
		model.addAttribute("cartItems",cart.getCartItems());
		model.addAttribute("totalAmount", cart.calculateTotal());

		return "cart";
	}
	
	@PostMapping("/newVehicle/add")
	public String addToCart_newVehicle(@RequestParam String productId, @RequestParam int quantity, Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		cartService.addItemToCart(user.getId(), productId, quantity);

		return "redirect:/newVehicle/" + productId;
	}

	@PostMapping("/usedVehicle/add")
	public String addToCart_usedVehicle(@RequestParam String productId, @RequestParam int quantity, Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		cartService.addItemToCart(user.getId(), productId, quantity);

		return "redirect:/usedVehicle/" + productId;
	}
	
	@PostMapping("/cart/update")
	public String updateQuantity(@RequestParam Long cartItem, @RequestParam int quantity) {
		

		cartService.updateQuantity(cartItem, quantity);


		return "redirect:/checkout";
	}
	
	@PostMapping("/cart/remove")
	public String removeItem(@RequestParam Long cartItem) {
		

		cartService.removeItem(cartItem);


		return "redirect:/cart";
	}
	@PostMapping("/cart/clear")
	public String clearCart(Principal principal) {
		

		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		cartService.clearCart(user.getId());


		return "redirect:/cart";
	}
}
