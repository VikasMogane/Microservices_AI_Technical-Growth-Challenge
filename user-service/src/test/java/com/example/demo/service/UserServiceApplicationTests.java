package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@SpringBootTest
class UserServiceApplicationTests {
	

    
	@Autowired
	private UserService userService;

//	@Test
//	void contextLoads() {
//	}
	
//	@Test
//	void testCreateUser() {
//		UserDTO userDTO = new UserDTO(4L,"vikas","vikas@example.com");
//		UserDTO savedUser = userService.createUser(userDTO);
//		assertNotNull(savedUser.getId());
//		
//	}

}
