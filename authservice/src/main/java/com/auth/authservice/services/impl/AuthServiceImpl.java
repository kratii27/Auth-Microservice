package com.auth.authservice.services.impl;

import com.auth.authservice.dtos.UserDTO;
import com.auth.authservice.services.AuthService;
import com.auth.authservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return user;
    }
}
