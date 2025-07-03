package com._onWheels._onWheels;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
    // private final AuthenticationManager authenticationManager;

    // public LoginController(AuthenticationManager authenticationManager) {
    //     this.authenticationManager = authenticationManager;
    // }

    // @PostMapping("/login")
    // public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
    //     Authentication authenticationRequest = UsernamePasswordAuthenticationToken
    //             .unauthenticated(loginRequest.username(), loginRequest.password());
    //     Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
    //     // ...
    // }

    // public record LoginRequest(String username, String password) {
    // }

    @GetMapping("/login")
    public String login() {
        return "login"; // renders login.html from /templates
    }
}
