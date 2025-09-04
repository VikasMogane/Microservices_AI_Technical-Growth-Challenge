package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository)
    {
  	  this.userRepository = userRepository;
  	  
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        User saved = userRepository.save(user);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
