package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtUtils;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest )
	{
		// for now we are using harcoded values
		
		if("vikas".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword()))
		{
			String token = jwtUtils.generateToken(loginRequest.getUsername());
			return ResponseEntity.ok(new LoginResponse(token));
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	}
	

}
