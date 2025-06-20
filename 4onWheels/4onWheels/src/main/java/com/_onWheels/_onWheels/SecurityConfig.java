package com._onWheels._onWheels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/HomePage")
                        .permitAll()  
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")  
                        .loginProcessingUrl("/login")  
                        .permitAll())
                .logout(logout -> logout
                        .permitAll()); 

        return http.build();
    }
}
