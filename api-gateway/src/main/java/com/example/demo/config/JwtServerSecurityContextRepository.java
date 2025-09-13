//package com.example.demo.config;
//
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextImpl;
//import org.springframework.security.web.server.context.ServerSecurityContextRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class JwtServerSecurityContextRepository implements ServerSecurityContextRepository {
//
//    private final JwtReactiveAuthenticationManager authManager;
//
//    public JwtServerSecurityContextRepository(JwtReactiveAuthenticationManager authManager) {
//        this.authManager = authManager;
//    }
//
//    @Override
//    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
//        return Mono.empty(); // stateless, no save needed
//    }
//
//    @Override
//    public Mono<SecurityContext> load(ServerWebExchange exchange) {
//        // Extract JWT token from headers
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String authToken = authHeader.substring(7);
//            return authManager.authenticate(new JwtAuthenticationToken(authToken))
//                    .map(SecurityContextImpl::new);
//        }
//        return Mono.empty();
//    }
//}
