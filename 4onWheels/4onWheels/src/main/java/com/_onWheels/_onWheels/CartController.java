package com._onWheels._onWheels;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class CartController {

	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/checkout")
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

		return "checkout";
	}
	
	@PostMapping("/cart/add")
	public String addToCart(@RequestParam String productId, @RequestParam int quantity, Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		cartService.addItemToCart(user.getId(), productId, quantity);


		return "redirect:/checkout";
	}
	
	@PostMapping("/cart/update")
	public String updateQuantity(@RequestParam Long cartItem, @RequestParam int quantity) {
		

		cartService.updateQuantity(cartItem, quantity);


		return "redirect:/checkout";
	}
	
	@PostMapping("/cart/remove")
	public String removeItem(@RequestParam Long cartItem) {
		

		cartService.removeItem(cartItem);


		return "redirect:/checkout";
	}
	@PostMapping("/cart/clear")
	public String clearCart(Principal principal) {
		

		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		cartService.clearCart(user.getId());


		return "redirect:/checkout";
	}
}
