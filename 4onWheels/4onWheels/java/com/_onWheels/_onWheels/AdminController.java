package com._onWheels._onWheels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/sales")
    public String sales() {
        return "sales"; // renders sales.html from /templates
    }
    
    @GetMapping("/admin/health")
    public String health() {
    	return "health";
    }
}
