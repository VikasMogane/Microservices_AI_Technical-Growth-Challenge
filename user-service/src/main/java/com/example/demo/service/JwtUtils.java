package com.example.demo.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Component
public class JwtUtils {
    private final String secret = "MySuperSecretKeyThatIsAtLeast32Chars1234"; // move to application.properties later
    private final long expirationMs = 1000 * 60 * 60; // 1 hour

    private SecretKey getSigningKey() {
        // Convert secret to a secure key
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}




































/* @Component
public class JwtUtils {
	
	
	private final String secret ="mysecretkey123";          // secret key
	private final Long expirationDate = (long) (1000*60*60); // 1hour
	
	
	public String generateToken(String username, String role) {
		
		return Jwts.builder().setSubject(username).claim(role, "role")
		.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+ expirationDate))
		.signWith(SignatureAlgorithm.HS256,secret).compact();
				
	}
	
	public Claims extractClaims(String token)
	{
	   return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();	
	}
	
	public String extractUsername(String token)
	{
		return extractClaims(token).getSubject();
	}
	
	public String exctractRoles(String token)
	{
		return (String) extractClaims(token).get("role");
	}

}  */
