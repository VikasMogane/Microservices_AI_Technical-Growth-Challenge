package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    
	   @Autowired
	    private MockMvc mockMvc;

	    // Mock the service so controller can run
	    @MockBean
	    private UserService userService;
	
	@Test
	void getUsers() throws Exception
	{
		mockMvc.perform(get("/users")).andExpect(status().isOk());
        log.info("✅ Test for GET /users executed successfully");

	}
}
