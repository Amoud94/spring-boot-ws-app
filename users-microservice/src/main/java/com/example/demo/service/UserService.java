package com.example.demo.service;

import com.example.demo.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserDetailsByEmail(String email);
}
