package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    private final JwtReactiveAuthenticationManager authManager;
    private final JwtServerSecurityContextRepository securityContextRepository;

    public SecurityConfig(JwtReactiveAuthenticationManager authManager,
                          JwtServerSecurityContextRepository securityContextRepository) {
        this.authManager = authManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        AuthenticationWebFilter jwtAuthWebFilter = new AuthenticationWebFilter(authManager);
        jwtAuthWebFilter.setServerAuthenticationConverter(new JwtServerAuthenticationConverter());
        jwtAuthWebFilter.setSecurityContextRepository(securityContextRepository);

        http.csrf(csrf -> csrf.disable())
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**", "/h2-console/**").permitAll()
                .anyExchange().authenticated()
            )
            .addFilterAt(jwtAuthWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
