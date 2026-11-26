package com.auth.authservice.services;

import com.auth.authservice.dtos.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO getUserByEmailId(String email);

    UserDTO updateUser(UserDTO user, String userId);

    void deleteUser(String userId);

    UserDTO getUserById(String userId);

    Iterable<UserDTO> getAllUsers();

}
