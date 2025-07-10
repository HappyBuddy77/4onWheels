package com._onWheels._onWheels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {

    @Autowired
    private CartRepository cartRepository;

   /* @GetMapping("/checkout")
    public String checkout(Model model) {
        // Get user's cart and add to model(database)
        return "checkout"; // renders checkout.html(not yet created)
    }
    
    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute CheckoutRequest checkoutRequest) {
        // Process checkout(not yet  created)
        System.out.println("Checkout processed for: " + checkoutRequest.getFirstName());
        return "checkout-success";
    }*/
}
