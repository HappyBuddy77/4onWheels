package com._onWheels._onWheels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    

    @GetMapping("/register")
    public String register() {
        return "register"; // serves register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDto user) {
        try {
            userService.registerUser(new UserRegistrationDto(
                    user.getEmail(), user.getPassword(), user.getFirstName(),
                    user.getLastName()));
            return "login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "register";
        }
    }
}
