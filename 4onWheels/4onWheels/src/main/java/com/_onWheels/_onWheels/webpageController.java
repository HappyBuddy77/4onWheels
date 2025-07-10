package com._onWheels._onWheels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  
public class webpageController {

    @GetMapping("/newVehicle")  
    public String printNewVehicle() {
        return "newVehicle";
    }

    @GetMapping("/usedVehicle")  
    public String printUsedVehicle() {
        return "usedVehicle";
    }
    @GetMapping("/cart")  
    public String printCart() {
        return "cart";
    }

}