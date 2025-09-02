package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;

@Service
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
}