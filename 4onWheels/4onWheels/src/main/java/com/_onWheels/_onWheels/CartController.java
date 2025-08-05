package com._onWheels._onWheels;


import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com._onWheels._onWheels.users.User;
import com._onWheels._onWheels.users.UserRepository;



@Controller

public class CartController {

	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private CartItemRepository itemRepository;

	
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
	public String addToCart_newVehicle(	@RequestParam String productId, @RequestParam int quantity, 
										@RequestParam String color_hidden, @RequestParam String battery_capacity_hidden, 
										@RequestParam String range_hidden, @RequestParam String charging_time_hidden, 
										@RequestParam String top_speed_hidden, @RequestParam String acceleration_hidden, Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(Long.valueOf(productId));		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);
		}
		Vehicle vehicle;
		
		if(vehicleOpt.isPresent()) {
			vehicle = vehicleOpt.get();
			if(vehicle.getQty() >= quantity) {
				cartService.addItemToCart(user.getId(), productId, quantity, color_hidden, battery_capacity_hidden, range_hidden, charging_time_hidden, top_speed_hidden, acceleration_hidden);

				vehicle.setQty(vehicle.getQty() - quantity);
				vehicleRepository.save(vehicle);
 			}
			else {
			}			
		}
		return "redirect:/newVehicle/" + productId;
	}

	@PostMapping("/usedVehicle/add")
	public String addToCart_usedVehicle(@RequestParam String productId, @RequestParam String quantity, 
										@RequestParam String color_hidden, @RequestParam String battery_capacity_hidden, 
										@RequestParam String range_hidden, @RequestParam String charging_time_hidden, 
										@RequestParam String top_speed_hidden, @RequestParam String acceleration_hidden, 
										Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(Long.valueOf(productId));		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);
		}

		Vehicle vehicle;
		int qty = 0;

		if(vehicleOpt.isPresent()) {
			vehicle = vehicleOpt.get();
			qty = Integer.parseInt(quantity);
			if(vehicle.getQty() >= qty) {
				cartService.addItemToCart(user.getId(), productId, qty, color_hidden, battery_capacity_hidden, range_hidden, charging_time_hidden, top_speed_hidden, acceleration_hidden);

				vehicle.setQty(vehicle.getQty() - qty);
				vehicleRepository.save(vehicle);
 			}
			else {
			}			
		}
		return "redirect:/usedVehicle/" + productId;
	}
	
	@PostMapping("/cart/update")
	public String updateQuantity(@RequestParam Long cartItem, @RequestParam int quantity) {
		

		cartService.updateQuantity(cartItem, quantity);


		return "redirect:/checkout";
	}
	
	@PostMapping("/cart/remove")
	public String removeItem(@RequestParam Long cartItem) {
		
		Optional<CartItem> itemOpt = itemRepository.findById(Long.valueOf(cartItem));
		CartItem item;
		
		if(itemOpt.isPresent()) {
			item = itemOpt.get();
			String evID = item.getProductId();
			Optional<Vehicle> vehicleOpt = vehicleRepository.findById(Long.valueOf(evID));
			Vehicle vehicle;

			if(vehicleOpt.isPresent()) {
				vehicle = vehicleOpt.get();
				vehicle.setQty(vehicle.getQty() + item.getQuantity());
				vehicleRepository.save(vehicle);		
			}
		}
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
