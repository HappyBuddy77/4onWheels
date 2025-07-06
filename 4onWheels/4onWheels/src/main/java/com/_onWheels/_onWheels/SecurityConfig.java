package com._onWheels._onWheels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/register", "/login")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .httpBasic(Customizer.withDefaults())
                                .formLogin(form -> form
                                                .loginPage("/login") // Use your custom login page
                                                .usernameParameter("email")
                                                .permitAll())
                                .logout(logout -> logout.permitAll());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return new CustomUserDetailsService();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        UserDetailsService userDetailsService,
                        PasswordEncoder passwordEncoder) {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);

                return new ProviderManager(authenticationProvider);
        }
}
