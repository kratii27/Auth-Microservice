package com.auth.authservice.services;

import com.auth.authservice.dtos.UserDTO;

public interface AuthService {

    UserDTO registerUser(UserDTO userDTO);
}
