package com._onWheels._onWheels.users;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com._onWheels._onWheels.Order;
import com._onWheels._onWheels.OrderDTO;
import com._onWheels._onWheels.OrderRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    
    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName());
        System.out.println(user);
        List<Order> orders = orderRepository.findByUserId(user.getId());
        
        
        UserDTO dto = UserDTO.builder()
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .numebrOfOrders(orders.size())
            .orders(OrderDTO.toList(orders))
            .build();
        
        model.addAttribute("user",dto);
        return "profile";
    }


}
