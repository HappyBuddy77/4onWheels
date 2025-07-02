package com._onWheels._onWheels;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

@Controller  
public class HomePage {

    @GetMapping("/HomePage")  
    public String printMessage() {
        return "home";
    }
}
