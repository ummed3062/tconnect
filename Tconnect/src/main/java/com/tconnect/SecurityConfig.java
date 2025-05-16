package com.tconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/check", "/users/register", "/users/login", "/users/changePass", "/users/sendOtp/{email}", "/users/verifyOtp/{email}/{otp}", "/jobs/*").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(); // Optional: enables basic auth for protected endpoints
        return http.build();
    }
}

