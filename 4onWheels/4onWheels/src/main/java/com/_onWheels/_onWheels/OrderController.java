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
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/order")
	public String viewUserOrder(Principal principal,Model model) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		List<Order> orders = orderRepository.findByUserId(user.getId());
		
		model.addAttribute("orders",orders);
		model.addAttribute("user",user);

		return "order";
	}
	
	@PostMapping("/checkout/process")
	public String processCheckout(@ModelAttribute CheckoutRequest checkoutRequest,Principal principal) {
		
		String userEmail = principal.getName();

		User user = userRepository.findByEmail(userEmail);
		
		if(user == null) {
            throw new RuntimeException("User not found: " + userEmail);

		}
		Cart cart = cartService.getCartByUserId(user.getId());

		if(cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot checkout empty cart: ");
		}
		
		Order order = new Order(user.getId(),Order.generateOrderNumber(),cart.calculateTotal());
		
	    order.setBillingFirstName(checkoutRequest.getFirstName());
	    order.setBillingLastName(checkoutRequest.getLastName());
	    order.setBillingEmail(checkoutRequest.getEmail());
	    order.setBillingPhone(checkoutRequest.getPhone());
	    order.setBillingAddress(checkoutRequest.getAddress());
	    order.setBillingCity(checkoutRequest.getCity());
	    order.setBillingProvince(checkoutRequest.getProvince());
	    order.setBillingPostalCode(checkoutRequest.getPostal_code());
	    order.setPaymentMethod(checkoutRequest.getPaymentMethod());
	    order.setPickupLocation(checkoutRequest.getPickupLocation());
	    order.setPickupDate(checkoutRequest.getPickupDate());
	    order.setPickupTime(checkoutRequest.getPickupTime());
	    
	    order = orderRepository.save(order);
	    
	    for(CartItem cartItem: cart.getCartItems()) {
	    	
	    	OrderItem orderItem = new OrderItem(order,cartItem.getProductName(),cartItem.getProductId(),cartItem.getQuantity(),cartItem.getPrice());
	    	
	    	orderItemRepository.save(orderItem);
	    }


	    cartService.clearCart(user.getId());

	    return "redirect:/order/confirmation/" + order.getOrderNumber();
	}
	
	@GetMapping("/order/confirmation/{orderNumber}")
	public String orderConfirmation(@PathVariable String orderNumber, 
	                               Principal principal, Model model) {
	    
	    String userEmail = principal.getName();
	    User user = userRepository.findByEmail(userEmail);
	    
	    if(user == null) {
	        throw new RuntimeException("User not found: " + userEmail);
	    }
	    
	    Optional<Order> orderOpt = orderRepository.findByOrderNumber(orderNumber);
	    
	    if(orderOpt.isPresent()) {
	        Order order = orderOpt.get();
	        
	        if(!order.getUserId().equals(user.getId())) {
	            throw new RuntimeException("Access denied");
	        }
	        
	        model.addAttribute("order", order);
	        model.addAttribute("orderItems", order.getOrderItems());
	        
	        return "order-confirmation"; 
	    }
	    
	    return "redirect:/order";
	}
	
}
