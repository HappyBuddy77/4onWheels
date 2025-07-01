package com._onWheels._onWheels;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class HomePage {

    @GetMapping("/HomePage")  
    public String printMessage() {
        return "Hello, Vehicle Home Page";
    }
}
