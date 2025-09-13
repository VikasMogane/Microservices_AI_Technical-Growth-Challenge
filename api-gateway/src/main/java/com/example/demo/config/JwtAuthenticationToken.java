package com.example.demo.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public JwtAuthenticationToken(String token) {
        super(List.of(new SimpleGrantedAuthority("ROLE_USER"))); // simple example
        this.token = token;
        setAuthenticated(true); // mark as authenticated after validation
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token; // Or username extracted from JWT
    }
}
