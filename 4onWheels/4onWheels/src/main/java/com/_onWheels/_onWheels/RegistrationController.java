package com._onWheels._onWheels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto user) {
        try {

            User u = userService.registerUser(new UserRegistrationDto(
                    user.getEmail(), user.getPassword(), user.getFirstName(),
                    user.getLastName()));

            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.badRequest().body(user);
    }
}
