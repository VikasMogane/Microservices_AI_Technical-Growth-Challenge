//package com.example.demo.config;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
//
//    @Override
//    public Mono<Authentication> convert(ServerWebExchange exchange) {
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            return Mono.just(new JwtAuthenticationToken(token));
//        }
//        return Mono.empty();
//    }
//}
