package com.example.demo.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // Here you validate JWT token and return authenticated object
        String token = authentication.getCredentials().toString();
        // Validate token logic here (call JwtUtils or your service)
        // Return Mono.just(authenticated) if valid, Mono.empty() if invalid
        return Mono.just(authentication); // Simplified
    }
}
