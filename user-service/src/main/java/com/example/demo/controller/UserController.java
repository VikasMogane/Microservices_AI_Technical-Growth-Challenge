package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("{id}")
    public  ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id ,@RequestBody UserDTO  userDTO)
    {
    	
    	return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)
    {
    	userService.deleteUserById(id);
    	
    	return ResponseEntity.ok("User Deleted Successfully");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}